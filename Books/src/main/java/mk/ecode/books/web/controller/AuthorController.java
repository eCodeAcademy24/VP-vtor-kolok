package mk.ecode.books.web.controller;

import mk.ecode.books.model.Author;
import mk.ecode.books.service.AuthorService;
import mk.ecode.books.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/authors")
    public String getAuthorsPage(@RequestParam String bookIsbn, Model model) {
        List<Author> authors = authorService.listAuthors();

        model.addAttribute("isbn", bookIsbn);
        model.addAttribute("authors", authors);
        return "authorList.html";
    }

    @PostMapping("/authors")
    public String addAuthorToBook(@RequestParam(name = "isbn") String bookIsbn,
                                  @RequestParam Long authorId) {
        bookService.addAuthorToBook(authorId, bookIsbn);
        return "redirect:/bookDetails?bookIsbn=" + bookIsbn;
    }
}
