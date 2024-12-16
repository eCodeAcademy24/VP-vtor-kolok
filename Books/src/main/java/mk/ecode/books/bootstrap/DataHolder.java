package mk.ecode.books.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ecode.books.model.Author;
import mk.ecode.books.model.Book;
import mk.ecode.books.model.BookStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Author> authors = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static List<BookStore> bookStores = new ArrayList<>();

    @PostConstruct
    public void init() {
        BookStore bookStore1 = new BookStore("Store 1", "City 1", "Addr 1");
        BookStore bookStore2 = new BookStore("Store 2", "City 2", "Addr 2");
        BookStore bookStore3 = new BookStore("Store 3", "City 3", "Addr 3");
        BookStore bookStore4 = new BookStore("Store 4", "City 4", "Addr 4");
        BookStore bookStore5 = new BookStore("Store 5", "City 5", "Addr 5");
        bookStores.add(bookStore1);
        bookStores.add(bookStore2);
        bookStores.add(bookStore3);
        bookStores.add(bookStore4);
        bookStores.add(bookStore5);

        authors.add(new Author(1L, "Author", "1", "bio 1"));
        authors.add(new Author(2L, "Author", "2", "bio 2"));
        authors.add(new Author(3L, "Author", "3", "bio 3"));
        authors.add(new Author(4L, "Author", "4", "bio 4"));
        authors.add(new Author(5L, "Author", "5", "bio 5"));

        books.add(new Book("isbn 1", "Title 1", "Genre 1", 2002, bookStore1));
        books.add(new Book("isbn 2", "Title 2", "Genre 2", 2003, bookStore2));
        books.add(new Book("isbn 3", "Title 3", "Genre 3", 2004, bookStore3));
        books.add(new Book("isbn 4", "Title 4", "Genre 4", 2005, bookStore4));
        books.add(new Book("isbn 5", "Title 5", "Genre 5", 2006, bookStore5));
    }
}
