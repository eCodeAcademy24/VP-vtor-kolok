package mk.ecode.books.service;

import mk.ecode.books.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();

    Book addAuthorToBook(Long authorId, String isbn);

    Book findBookByIsbn(String isbn);

    void saveBook(String title, String isbn, String genre, Integer year, Long bookStoreId);

    void deleteBookById(Long id);

    Optional<Book> findById(Long id);

    void editBook(Long id, String title, String isbn, String genre, Integer year, Long bookStoreId);
}
