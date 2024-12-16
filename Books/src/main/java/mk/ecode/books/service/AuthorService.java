package mk.ecode.books.service;

import mk.ecode.books.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();

    Author findById(Long authorId);
}
