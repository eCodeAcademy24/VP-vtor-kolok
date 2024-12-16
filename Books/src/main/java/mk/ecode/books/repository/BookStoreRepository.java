package mk.ecode.books.repository;

import mk.ecode.books.bootstrap.DataHolder;
import mk.ecode.books.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookStoreRepository {

    public List<BookStore> findAll() {
        return DataHolder.bookStores;
    }

    public Optional<BookStore> findById(Long bookStoreId) {
        return DataHolder.bookStores.stream()
                .filter(bookStore -> bookStore.getId().equals(bookStoreId))
                .findFirst();
    }
}
