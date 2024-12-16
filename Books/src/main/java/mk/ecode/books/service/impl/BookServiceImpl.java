package mk.ecode.books.service.impl;

import mk.ecode.books.model.Author;
import mk.ecode.books.model.Book;
import mk.ecode.books.model.BookStore;
import mk.ecode.books.repository.BookRepository;
import mk.ecode.books.service.AuthorService;
import mk.ecode.books.service.BookService;
import mk.ecode.books.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookStoreService bookStoreService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, BookStoreService bookStoreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookStoreService = bookStoreService;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addAuthorToBook(Long authorId, String isbn) {
        Author author = authorService.findById(authorId);
        Book book = findBookByIsbn(isbn);
        return bookRepository.addAuthorToBook(author, book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public void saveBook(String title,
                         String isbn,
                         String genre,
                         Integer year,
                         Long bookStoreId) {
        BookStore bookStore = bookStoreService.findById(bookStoreId);
        bookRepository.save(title, isbn, genre, year, bookStore);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteBookById(id);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void editBook(Long id, String title, String isbn, String genre, Integer year, Long bookStoreId) {
        BookStore bookStore = bookStoreService.findById(bookStoreId);
        bookRepository.editBook(id, title, isbn, genre, year, bookStore);
    }
}
