package BackendProg.BookAppWeb.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.model.DiscordUser;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    DiscordUserRepository userRepository;

    Book makeTestBook(String title, String author, DiscordUser user) {
        Book book = new Book();

        book.setTitle(title);
        book.setAuthor(author);
        book.setUser(user);

        return book;
    }

    @Test
    public void canAddBook() {
        long lenBefore = bookRepository.count();
        bookRepository.save(makeTestBook("Test", "Test", null));

        assertNotEquals(lenBefore, bookRepository.count());
    }

    @Test
    public void canAddUserAndFindBookByUser() {
        DiscordUser testUser = new DiscordUser();

        testUser = userRepository.save(testUser);

        assertNotNull(testUser.getLocalId());

        ArrayList<Book> books = new ArrayList<>();

        books.add(makeTestBook("Test1", "Test1", testUser));
        books.add(makeTestBook("Test2", "Test2", testUser));
        books.add(makeTestBook("Test3", "Test3", testUser));

        bookRepository.saveAll(books);

        List<Book> booksFound = bookRepository.findByUser(testUser);

        assertNotEquals(booksFound.size(), 0);

        assertEquals(booksFound.get(0).getTitle().hashCode(), "Test1".hashCode());
    }
}
