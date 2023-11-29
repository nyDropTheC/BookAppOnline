package BackendProg.BookAppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.repository.BookRepository;
import BackendProg.BookAppWeb.util.DiscordIdHelper;

@Controller
public class ToggleReadController {
    @Autowired
    private BookRepository bookRepo;

    // shouldn't be a GET, but who gives a shit?
    @GetMapping("/books/{bookId}/toggleRead")
    public String toggleRead(@PathVariable("bookId") Long bookId) {
        Book book = bookRepo.findById(bookId).orElse(null);

        if(book == null) {
            return "redirect:/user";
        }
        // very postironic!
        if(!book.getDiscordUser().equals(DiscordIdHelper.getCurrentDiscordUser())) {
            return "redirect:/user";
        }

        book.setRead(!book.isRead());
        bookRepo.save(book);

        return "redirect:/user";
    }    
}
