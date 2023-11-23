package BackendProg.BookAppWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private ClientRegistration discordClientRegistration() {
        return ClientRegistration
                    .withRegistrationId("discord")
                    .clientName("Discord")
                    .clientId(System.getenv("BOOKAPP_OAUTH_DISCORD_CLIENT_ID"))
                    .clientSecret(System.getenv("BOOKAPP_OAUTH_DISCORD_CLIENT_SECRET"))
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                    .scope("identify")
                    .authorizationUri("https://discord.com/oauth2/authorize")
                    .tokenUri("https://discord.com/api/oauth2/token")
                    .userInfoUri("https://discord.com/api/users/@me")
                    .userNameAttributeName("username")
                    .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(discordClientRegistration());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        // oauth2 crap goes here
        
        http
            .authorizeHttpRequests((httpSec) -> httpSec.anyRequest().authenticated())
            .oauth2Login( withDefaults()
                    //(oauth2) -> oauth2
                    //    .clientRegistrationRepository(clientRegistrationRepository())
                    //    .authorizedClientService(authorizedClientService())
                    //    .loginPage("/oauth2/authorization/discord")
            );
        return http.build();
    }
}