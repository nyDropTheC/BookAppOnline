package BackendProg.BookAppWeb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.repository.BookRepository;
import BackendProg.BookAppWeb.util.DiscordIdHelper;
import BackendProg.BookAppWeb.util.OpenAiComms;

@RestController
public class OpenAiBookRequestController {
    @Autowired
    private BookRepository bookRepo;

    private OpenAiComms comms;

    public OpenAiBookRequestController() {
        this.comms = new OpenAiComms();
    }
    
    // Not a GET, does not follow rules of GET (e.g. won't cache right, affects stuff in DB and shit)
    // But Spring Security breaks when it's a POST request for some reason
    // NVM maybe it won't break if we call it from the actual website instead of Postman?

    @GetMapping("/api/openai/summarize/{bookId}")
    public String summarizeBook(@PathVariable("bookId") Long bookId) {
        Book book = bookRepo.findById(bookId).orElse(null);
        String discordUser = DiscordIdHelper.getCurrentDiscordUser(); // Curious lol
        // this is horrifying and retarded
        if(book == null) {
            return "";
        }

        // and not secure at all, given an user can just bullshit their Discord ID
        if(!book.getDiscordUser().equals(discordUser)) {
            return "";
        }

        // This has a hole
        // People can delete their books and get the refreshcount down to 0 again
        if(book.getDescRefreshCount() > 3) {
            return "";
        }

        String aiResponse = comms.getBookRecommendationText(book.getTitle(), book.getAuthor());

        book.setDescRefreshCount(book.getDescRefreshCount() + 1);
        book.setAiGeneratedDesc(aiResponse);

        bookRepo.save(book);

        return aiResponse;
    }
}
