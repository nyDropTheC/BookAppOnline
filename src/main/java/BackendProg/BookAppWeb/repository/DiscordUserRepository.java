package BackendProg.BookAppWeb.repository;

import org.springframework.data.repository.CrudRepository;

import BackendProg.BookAppWeb.model.DiscordUser;

public interface DiscordUserRepository extends CrudRepository<DiscordUser, Long> {
    
}
