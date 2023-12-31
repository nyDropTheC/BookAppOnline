package BackendProg.BookAppWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
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
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> userService() {
        DefaultOAuth2UserService userService = new DefaultOAuth2UserService();

        return userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        // oauth2 crap goes here
        
        http
            .authorizeHttpRequests((httpSec) -> 
                httpSec
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).permitAll() // retarded bandaid fix
                    .anyRequest()
                    .authenticated()
            )
            .oauth2Login(withDefaults())
            .cors(withDefaults())
            .logout(logout -> 
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
            );
        return http.build();
    }
}