package BackendProg.BookAppWeb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import BackendProg.BookAppWeb.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByDiscordUser(String user);
}
