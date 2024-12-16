package mk.ecode.books.repository;

import mk.ecode.books.bootstrap.DataHolder;
import mk.ecode.books.model.Author;
import mk.ecode.books.model.Book;
import mk.ecode.books.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    public List<Book> findAll() {
        return DataHolder.books;
    }

    public Book findByIsbn(String isbn) {
        return DataHolder.books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow();
    }

    public Book addAuthorToBook(Author author, Book book) {
        book.getAuthors().add(author);
        return book;
    }

    public void save(String title, String isbn, String genre, Integer year, BookStore bookStore) {
        Book book = new Book(
                isbn,
                title,
                genre,
                year,
                bookStore
        );

        DataHolder.books.add(book);
    }

    public void deleteBookById(Long id) {
        DataHolder.books.removeIf(book -> book.getId().equals(id));
    }

    public Optional<Book> findById(Long id) {
        return DataHolder.books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public void editBook(Long id, String title, String isbn, String genre, Integer year, BookStore bookStore) {
        Book book = DataHolder.books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow();

        book.setTitle(title);
        book.setIsbn(isbn);
        book.setGenre(genre);
        book.setYear(year);
        book.setBookStore(bookStore);
    }
}
