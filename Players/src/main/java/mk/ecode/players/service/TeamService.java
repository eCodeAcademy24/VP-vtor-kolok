package mk.ecode.players.service;

import mk.ecode.players.model.Team;
import mk.ecode.players.model.exceptions.InvalidTeamIdException;

import java.util.List;

public interface TeamService {

    /**
     * returns the team with the given id
     *
     * @param id The id of the team that we want to obtain
     * @return
     * @throws InvalidTeamIdException when there is no team with the given id
     */
    Team findById(Long id);

    /**
     * @return List of all teams in the database
     */
    List<Team> listAll();

    /**
     * This method is used to create a new team, and save it in the database.
     *
     * @param name
     * @return The team that is created. The id should be generated when the team is created.
     */
    Team create(String name);
}
