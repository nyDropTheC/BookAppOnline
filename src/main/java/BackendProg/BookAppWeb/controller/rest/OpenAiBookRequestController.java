package BackendProg.BookAppWeb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.repository.BookRepository;
import BackendProg.BookAppWeb.util.OpenAiComms;

@RestController
public class OpenAiBookRequestController {
    @Autowired
    BookRepository bookRepo;

    OpenAiComms comms;

    public OpenAiBookRequestController() {
        this.comms = new OpenAiComms();
    }

    @PostMapping("/api/openai/summarize/{bookId}")
    public String summarizeBook(@RequestHeader(HttpHeaders.AUTHORIZATION) String userId, @PathVariable("bookId") Long bookId) {
        Book book = bookRepo.findById(bookId).orElse(null);

        // this is horrifying and retarded
        if(book == null) {
            return "";
        }

        // and not secure at all, given an user can just bullshit their Discord ID
        if(book.getDiscordUser() != userId) {
            return "";
        }

        if(book.getDescRefreshCount() > 3) {
            return "";
        }

        String aiResponse = comms.getBookRecommendationText(book.getTitle(), book.getAuthor());

        book.setDescRefreshCount(book.getDescRefreshCount() + 1);
        book.setAiGeneratedDesc(aiResponse);

        return aiResponse;
    }
}
