package mk.ecode.movies.service;

import mk.ecode.movies.model.Director;
import mk.ecode.movies.model.exceptions.InvalidDirectorIdException;

import java.util.List;

public interface DirectorService {

    /**
     * returns the director with the given id
     *
     * @param id The id of the director that we want to obtain
     * @return
     * @throws InvalidDirectorIdException when there is no director with the given id
     */
    Director findById(Long id);

    /**
     * @return List of all directors in the database
     */
    List<Director> listAll();

    /**
     * This method is used to create a new director, and save it in the database.
     *
     * @param name
     * @return The director that is created. The id should be generated when the director is created.
     */
    Director create(String name);
}
