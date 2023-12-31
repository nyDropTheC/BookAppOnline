package BackendProg.BookAppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.repository.BookRepository;
import BackendProg.BookAppWeb.util.DiscordIdHelper;

@Controller
public class BookPageController {
    @Autowired
    private BookRepository bookRepo;

    @GetMapping("/books/{bookId}")
    String bookPage(@PathVariable("bookId") Long bookId, Model model) {
        Book book = bookRepo.findById(bookId).orElse(null);

        if(book == null) {
            return "redirect:/user";
        }

        // I know this is a stupid way of doing security
        // BUT
        // I don't care
        if(!book.getDiscordUser().equals(DiscordIdHelper.getCurrentDiscordUser())) {
            return "redirect:/user";
        }

        model.addAttribute("book", book);

        return "book";
    }
}
