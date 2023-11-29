package BackendProg.BookAppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.repository.BookRepository;
import BackendProg.BookAppWeb.util.DiscordIdHelper;

@Controller
public class RemoveBookController {
    @Autowired
    BookRepository bookRepo;

    @GetMapping("books/{bookId}/remove")
    String removeBook(@PathVariable("bookId") Long bookId) {
        Book book = bookRepo.findById(bookId).orElse(null);

        if(book == null) {
            return "redirect:/user";
        } 
        else if(!book.getDiscordUser().equals(DiscordIdHelper.getCurrentDiscordUser())) {
            return "redirect:/user";
        }

        bookRepo.deleteById(bookId);

        return "redirect:/user";
    }
}
