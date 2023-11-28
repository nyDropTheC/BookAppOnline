package BackendProg.BookAppWeb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscordAuthController {
    @GetMapping("/")
    String defaultMapping() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return "discordTest";
    }
}