package BackendProg.BookAppWeb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    @ManyToOne
    @JoinColumn(name="localid")
    private DiscordUser user;

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

    public DiscordUser getUser() {
        return user;
    }

    public void setUser(DiscordUser user) {
        this.user = user;
    }

    public Book() {
        
    }

    public Book(long id, String title, String author, String aiGeneratedDesc, long descRefreshCount, DiscordUser user) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.aiGeneratedDesc = aiGeneratedDesc;
        this.descRefreshCount = descRefreshCount;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", user=" + (user != null ? user.getDiscordId() : "none") + "]";
    }
}
