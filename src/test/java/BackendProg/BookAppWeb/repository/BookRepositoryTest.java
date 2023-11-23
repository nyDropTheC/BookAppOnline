package BackendProg.BookAppWeb.repository;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import BackendProg.BookAppWeb.model.Book;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    DiscordUserRepository userRepository;

    @Test
    public void canAddBook() {
        long lenBefore = bookRepository.count();

        bookRepository.save(new Book(1, "Test", "Test", null, 0, null));

        assertNotEquals(lenBefore, bookRepository.count());
    }
}
