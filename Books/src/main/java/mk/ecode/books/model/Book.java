package mk.ecode.books.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Book {

    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private int year;
    private List<Author> authors;
    private BookStore bookStore;

    public Book(String isbn, String title, String genre, int year, BookStore bookStore) {
        this.id = (long) (Math.random() * 1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.bookStore = bookStore;
        authors = new ArrayList<>();
    }
}
