package BackendProg.BookAppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.repository.BookRepository;
import BackendProg.BookAppWeb.util.DiscordIdHelper;

@Controller
public class AddBookController {
    @Autowired
    private BookRepository bookRepo;

    // Not implementing editing is actually a conscious move on my part
    // As that'd screw up the AI stuff IMO

    @GetMapping("/books/add")
    public String addBookPage(Model model) {
        String discordId = DiscordIdHelper.getCurrentDiscordUser();

        Book newBook = new Book();

        newBook.setDiscordUser(discordId);
        model.addAttribute("book", newBook);

        return "addbook";
    }

    @PostMapping("/books/save")
    public String onSave(Book book) {
        bookRepo.save(book);

        return "redirect:/user";
    }
}

