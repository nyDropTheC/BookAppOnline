package BackendProg.BookAppWeb.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class DiscordIdHelper {
    public static String getCurrentDiscordUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal)auth.getPrincipal();
        String discordId = principal.getAttribute("id");

        return discordId;
    }
}
