package BackendProg.BookAppWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.repository.BookRepository;

@Controller
public class UserPageController {
    @Autowired
    BookRepository bookRepo;

    @GetMapping("/user")
    String userPageController(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal)auth.getPrincipal();
        String discordId = principal.getAttribute("id");

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
