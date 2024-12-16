package mk.ecode.books.web.controller;

import mk.ecode.books.model.Book;
import mk.ecode.books.service.BookService;
import mk.ecode.books.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookController(BookService bookService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        List<Book> books = bookService.listBooks();
        model.addAttribute("books", books);

        if (error != null) {
            model.addAttribute("error", error);
        }

        return "listBooks.html";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long bookStoreId) {
        bookService.saveBook(
                title,
                isbn,
                genre,
                year,
                bookStoreId
        );

        return "redirect:/books";
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("bookStores", bookStoreService.findAll());
        return "add-form.html";
    }

    @GetMapping("/delete/{id}")
    public String deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("bookStores", bookStoreService.findAll());

            return "add-form.html";
        }

        return "redirect:/books?error=BookNotFound";

    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long bookStoreId) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            bookService.editBook(id, title, isbn, genre, year, bookStoreId);
            return "redirect:/books";
        }

        return "redirect:/books?error=bookNotFound";
    }
}
