package BackendProg.BookAppWeb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String aiGeneratedDesc;

    @Column
    private long descRefreshCount;

    @Column
    private boolean isRead;

    // I'm dumb and couldn't figure out a way to store OAuth2 crap in the database, so this meme it is
    // It's completely retarded
    @Column
    private String discordUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAiGeneratedDesc() {
        return aiGeneratedDesc;
    }

    public void setAiGeneratedDesc(String aiGeneratedDesc) {
        this.aiGeneratedDesc = aiGeneratedDesc;
    }

    public long getDescRefreshCount() {
        return descRefreshCount;
    }

    public void setDescRefreshCount(long descRefreshCount) {
        this.descRefreshCount = descRefreshCount;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getDiscordUser() {
        return discordUser;
    }

    public void setDiscordUser(String user) {
        this.discordUser = user;
    }

    public Book() {
        
    }

    public Book(long id, String title, String author, String aiGeneratedDesc, long descRefreshCount, String user) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.aiGeneratedDesc = aiGeneratedDesc;
        this.descRefreshCount = descRefreshCount;
        this.discordUser = user;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", user=" + (discordUser != null ? discordUser : "none") + "]";
    }
}
