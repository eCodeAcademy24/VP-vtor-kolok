package mk.ecode.books.service.impl;

import mk.ecode.books.model.Author;
import mk.ecode.books.repository.AuthorRepository;
import mk.ecode.books.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow();
    }
}
