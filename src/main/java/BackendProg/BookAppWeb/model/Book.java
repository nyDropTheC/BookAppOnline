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
    long id;

    @Column
    String title;

    @Column
    String author;

    @Column
    String aiGeneratedDesc;

    @Column
    long descRefreshCount;

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

    public Book() {
        
    }

    public Book(long id, String title, String author, String aiGeneratedDesc, long descRefreshCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.aiGeneratedDesc = aiGeneratedDesc;
        this.descRefreshCount = descRefreshCount;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
    }
}
