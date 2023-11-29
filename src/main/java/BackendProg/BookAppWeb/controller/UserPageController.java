package BackendProg.BookAppWeb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.repository.BookRepository;
import BackendProg.BookAppWeb.util.DiscordIdHelper;

@Controller
public class UserPageController {
    @Autowired
    private BookRepository bookRepo;

    @GetMapping("/user")
    public String userPageController(Model model) {
        String discordId = DiscordIdHelper.getCurrentDiscordUser();

        List<Book> booksThisIdHas = bookRepo.findByDiscordUser(discordId);

        // Couldn't be arsed for a clean way to do this in Thymeleaf
        if(booksThisIdHas.size() == 0) {
            Book mockBook = new Book();

            mockBook.setTitle("No books added :(");
            mockBook.setAuthor("Go out and add some!");
            
            booksThisIdHas.add(mockBook);
        }

        model.addAttribute("booksThisIdHas", booksThisIdHas);
        
        return "user";
    }
}
