package mk.ecode.movies.service.impl;

import mk.ecode.movies.model.Movie;
import mk.ecode.movies.repository.MovieRepository;
import mk.ecode.movies.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        //TODO: some logic for the text
        return movieRepository.searchMovies(text);
    }

    @Override
    public List<Movie> filter(String searchText, String rating) {
        if (!searchText.isEmpty() && !rating.isEmpty()) {
            return movieRepository.findByNameContainingAndPopularityScoreGreaterThanEqual(searchText, Double.valueOf(rating));
        } else if (!searchText.isEmpty()) {
            return movieRepository.findByNameContaining(searchText);
        } else if (!rating.isEmpty()) {
            return movieRepository.findByPopularityScoreGreaterThanEqual(Double.valueOf(rating));
        }

        return listAll();
    }
}
