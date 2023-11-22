package BackendProg.BookAppWeb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import BackendProg.BookAppWeb.util.OpenAiComms;

@SpringBootApplication
public class BookAppWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAppWebApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			System.out.println("Hello from bookapponline!");
			
			OpenAiComms comms = new OpenAiComms();

			System.out.println(comms.getBookRecommendationText(null, null));
		};
	}
}
