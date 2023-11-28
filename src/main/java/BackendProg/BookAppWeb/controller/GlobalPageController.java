package BackendProg.BookAppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import BackendProg.BookAppWeb.repository.BookRepository;

public class GlobalPageController {
    @Autowired
    BookRepository bookRepo;

    @GetMapping("/global")
    String globalPage() {
        

        return "global";
    }
}
