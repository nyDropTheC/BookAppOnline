package BackendProg.BookAppWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddBookController {
    @GetMapping("/books/add")
    String addBookPage() {
        return "";
    }

    @GetMapping("/books/api/add")
    String addBookImpl() {
        return "redirect:/user";
    }
}
