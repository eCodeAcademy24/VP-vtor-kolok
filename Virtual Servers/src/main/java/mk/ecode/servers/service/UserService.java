package mk.ecode.servers.service;

import mk.ecode.servers.model.User;
import mk.ecode.servers.model.exceptions.InvalidUserIdException;

import java.util.List;

public interface UserService {

    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws InvalidUserIdException when there is no  location with the given id
     */
    User findById(Long id);

    /**
     * @return List of all entities in the database
     */
    List<User> listAll();

    /**
     * This method is used to create a new entity, and save it in the database.
     *
     * @param username
     * @return The entity that is created. The id should be generated when the entity is created.
     */
    User create(String username, String password, String role);
}
