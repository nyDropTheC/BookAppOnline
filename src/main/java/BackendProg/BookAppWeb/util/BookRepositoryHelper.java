package BackendProg.BookAppWeb.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import BackendProg.BookAppWeb.model.Book;

class BookEntry {
    private final String bookName;
    private final String bookHashedName;

    public BookEntry(Book book) {
        this.bookName = book.getTitle();
        this.bookHashedName = this.bookName.trim().toLowerCase();
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public int hashCode() {
        return bookHashedName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return ((BookEntry)obj).hashCode() == this.hashCode();
    }
}

class SortableBook {
    private final String bookName;
    private final Integer readCount;

    public SortableBook(BookEntry entry, Integer readCount) {
        this.bookName = entry.getBookName();
        this.readCount = readCount;
    }

    public String getBookName() {
        return bookName;
    }

    public Integer getReadCount() {
        return readCount;
    }
}

public class BookRepositoryHelper {
    // this is cringe
    static List<SortableBook> distinctReadBookTopTenList(List<Book> books) {
        HashMap<BookEntry, Integer> collection = new HashMap<>();
        
        for(Book n : books) {
            if(n.isRead()) {
                BookEntry e = new BookEntry(n);

                collection.merge(e, 1, Integer::sum);
            }
        }
        ArrayList<SortableBook> returnedBooks = new ArrayList<>();

        for(var n : collection.entrySet()) {
            returnedBooks.add(new SortableBook(n.getKey(), n.getValue()));
        }

        returnedBooks.sort(Comparator.comparing(SortableBook::getReadCount).reversed());

        return returnedBooks.subList(0, Math.min(returnedBooks.size(), 9));
    }
}
