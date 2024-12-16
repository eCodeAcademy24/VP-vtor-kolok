package mk.ecode.books.service;

import mk.ecode.books.model.BookStore;

import java.util.List;

public interface BookStoreService {

    List<BookStore> findAll();

    BookStore findById(Long bookStoreId);
}
