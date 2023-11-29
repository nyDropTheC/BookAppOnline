package BackendProg.BookAppWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.repository.BookRepository;
import BackendProg.BookAppWeb.util.BookRepositoryHelper;

@Controller
public class GlobalPageController {
    @Autowired
    BookRepository bookRepo;

    @GetMapping("/")
    String globalPage() {
        List<Book> books = bookRepo.findByIsRead(true);

        // THIS IS EVIL
        // SortableBook IS NOT EXPORTED ANYWHERE
        // I HATE JAVA
        var sortedByReadCount = BookRepositoryHelper.distinctReadBookTopTenList(books); 
        
        return "global";
    }
}
