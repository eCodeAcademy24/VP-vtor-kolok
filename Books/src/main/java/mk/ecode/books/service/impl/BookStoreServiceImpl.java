package mk.ecode.books.service.impl;

import mk.ecode.books.model.BookStore;
import mk.ecode.books.repository.BookStoreRepository;
import mk.ecode.books.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public BookStore findById(Long bookStoreId) {
        return bookStoreRepository.findById(bookStoreId).orElseThrow();
    }
}
