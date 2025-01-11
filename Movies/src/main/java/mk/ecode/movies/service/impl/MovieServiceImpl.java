package mk.ecode.movies.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.movies.model.Genre;
import mk.ecode.movies.model.Movie;
import mk.ecode.movies.model.exceptions.InvalidMovieIdException;
import mk.ecode.movies.repository.MovieRepository;
import mk.ecode.movies.service.DirectorService;
import mk.ecode.movies.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorService directorService;

    @Override
    public List<Movie> listAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
    }

    @Override
    public Movie create(String name, String description, Double rating, Genre genre, Long director) {
        Movie movie = new Movie();

        movie.setName(name);
        movie.setDescription(description);
        movie.setRating(rating);
        movie.setGenre(genre);
        movie.setDirector(directorService.findById(director));

        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, String name, String description, Double rating, Genre genre, Long director) {
        Movie movie = findById(id);

        movie.setName(name);
        movie.setDescription(description);
        movie.setRating(rating);
        movie.setGenre(genre);
        movie.setDirector(directorService.findById(director));

        return movieRepository.save(movie);
    }

    @Override
    public Movie delete(Long id) {
        Movie movie = findById(id);
        movieRepository.delete(movie);
        return movie;
    }

    @Override
    public Movie vote(Long id) {
        Movie movie = findById(id);
        movie.setVotes(movie.getVotes() + 1);
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> listMoviesWithRatingLessThenAndGenre(Double rating, Genre genre) {
        if (rating != null && genre != null) {
            return movieRepository.findAllByRatingLessThanAndGenre(rating, genre);
        } else if (rating != null) {
            return movieRepository.findAllByRatingLessThan(rating);
        } else if (genre != null) {
            return movieRepository.findAllByGenre(genre);
        }

        return listAllMovies();
    }
}
