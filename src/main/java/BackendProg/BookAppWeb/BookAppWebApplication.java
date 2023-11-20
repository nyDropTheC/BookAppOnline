package BackendProg.BookAppWeb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.theokanning.openai.service.OpenAiService;

@SpringBootApplication
public class BookAppWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAppWebApplication.class, args);
	}
}
