package BackendProg.BookAppWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private ClientRegistration discordClientRegistration() {
        return ClientRegistration
                    .withRegistrationId("discord")
                    .clientName("Discord")
                    .clientId(System.getenv("BOOKAPP_OAUTH_DISCORD_CLIENT_ID"))
                    .clientSecret(System.getenv("BOOKAPP_OAUTH_DISCORD_CLIENT_SECRET"))
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                    .scope("identify")
                    .authorizationUri("https://discord.com/oauth2/authorize")
                    .tokenUri("https://discord.com/api/oauth2/token")
                    .userNameAttributeName(IdTokenClaimNames.SUB)
                    .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(discordClientRegistration());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        // oauth2 crap goes here
        return http.build();
    }
}