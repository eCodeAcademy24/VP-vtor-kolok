package mk.ecode.movies.repository;

import mk.ecode.movies.model.Genre;
import mk.ecode.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByRatingLessThanAndGenre(Double rating, Genre genre);

    List<Movie> findAllByRatingLessThan(Double rating);

    List<Movie> findAllByGenre(Genre genre);
}
