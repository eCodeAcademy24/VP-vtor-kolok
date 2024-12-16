package mk.ecode.books.web.controller;

import mk.ecode.books.model.Book;
import mk.ecode.books.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookDetailsController {

    private final BookService bookService;

    public BookDetailsController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/bookDetails")
    public String getBookDetailsPage(@RequestParam String bookIsbn, Model model) {
        Book book = bookService.findBookByIsbn(bookIsbn);

        model.addAttribute("book", book);
        return "bookDetails.html";
    }
}
