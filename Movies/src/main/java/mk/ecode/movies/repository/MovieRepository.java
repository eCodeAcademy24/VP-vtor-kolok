package mk.ecode.movies.repository;

import jakarta.annotation.PostConstruct;
import mk.ecode.movies.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    public static List<Movie> movies = new ArrayList<>();

    @PostConstruct
    public void init() {
        movies.add(new Movie("Title 1", "Sum 1", 2.3));
        movies.add(new Movie("Movie 2", "Sum 2", 2.3));
        movies.add(new Movie("Title 3", "Sum 3", 2.3));
        movies.add(new Movie("Movie 4", "Sum 4", 3.4));
        movies.add(new Movie("Title 5", "Sum 5", 5.6));
        movies.add(new Movie("Movie 6", "Sum 6", 2.3));
        movies.add(new Movie("Title 7", "Sum 7", 8.19));
        movies.add(new Movie("Movie 8", "Sum 8", 9.9));
        movies.add(new Movie("Title 9", "Sum 9", 2.50));
        movies.add(new Movie("Movie 10", "Sum 10", 2.76));
    }

    public List<Movie> findAll() {
        return movies;
    }

    public List<Movie> searchMovies(String text) {
        return movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                        movie.getSummary().toLowerCase().contains(text.toLowerCase())
                )
                .collect(Collectors.toList());
    }

    public List<Movie> findByNameContainingAndPopularityScoreGreaterThanEqual(String searchText, Double rating) {
        return movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(searchText.toLowerCase())
                        && movie.getRating() >= rating
                )
                .collect(Collectors.toList());
    }

    public List<Movie> findByNameContaining(String searchText) {
        return movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> findByPopularityScoreGreaterThanEqual(Double rating) {
        return movies.stream()
                .filter(movie -> movie.getRating() >= rating)
                .collect(Collectors.toList());
    }
}
