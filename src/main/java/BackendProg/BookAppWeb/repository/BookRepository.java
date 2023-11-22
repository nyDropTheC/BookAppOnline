package BackendProg.BookAppWeb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import BackendProg.BookAppWeb.model.Book;
import BackendProg.BookAppWeb.model.DiscordUser;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByUser(DiscordUser user);
}
