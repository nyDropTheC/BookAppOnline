package BackendProg.BookAppWeb;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import BackendProg.BookAppWeb.model.Book;
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
			/*ArrayList<OpenAiComms> commsTestList = new ArrayList<>();
			
			Book[] books = new Book[]{ 
				new Book(1, "Worm", "Wildbow", "", 0, null),
				new Book(2, "Skin Game", "Jim Butcher", "", 0, null)
			};

			for(int i = 1; i <= 2; i++) {
				commsTestList.add(new OpenAiComms());
				System.out.println(commsTestList.getLast().getBookRecommendationText(books[i - 1].getTitle(), books[i - 1].getAuthor()));
			}*/
		};
	}
}
