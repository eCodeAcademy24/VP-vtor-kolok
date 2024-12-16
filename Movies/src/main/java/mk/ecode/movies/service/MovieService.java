package mk.ecode.movies.service;

import mk.ecode.movies.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();

    List<Movie> searchMovies(String text);

    List<Movie> filter(String searchText, String rating);
}
