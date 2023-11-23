package BackendProg.BookAppWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscordAuthController {
    @GetMapping("/")
    String authPage() {
        return "We reached this!";
    }
}