package BackendProg.BookAppWeb.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DiscordUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long localId;

    @Column(unique = true)
    private String discordId;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Book> books;

    public DiscordUser() {

    }

    public DiscordUser(long id, String discordId, String name) {
        this.localId = id;
        this.discordId = discordId;
        this.name = name;
    }

    public long getLocalId() {
        return localId;
    }

    public void setLocalId(long id) {
        this.localId = id;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
