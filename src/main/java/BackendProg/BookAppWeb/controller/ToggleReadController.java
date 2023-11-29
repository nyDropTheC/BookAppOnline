package BackendProg.BookAppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.repository.BookRepository;

@Controller
public class ToggleReadController {
    @Autowired
    BookRepository bookRepo;

    // shouldn't be a GET, but who gives a shit?
    @GetMapping("/books/{bookId}/toggleRead")
    String toggleRead(@PathVariable("bookId") Long bookId) {
        Book book = bookRepo.findById(bookId).orElse(null);

        if(book == null) {
            return "redirect:/user";
        }

        // very postironic!
        // not sure I can wrap it nicely actually lol
        // SecurityContextHolder might not be valid without being in a controller
        if(book.getDiscordUser() != ((OAuth2AuthenticatedPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttribute("id")) {
            return "redirect:/user";
        }

        book.setRead(!book.isRead());

        return "redirect:/user";
    }    
}
