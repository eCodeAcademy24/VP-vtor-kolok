package mk.ecode.players.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.players.model.Player;
import mk.ecode.players.model.PlayerPosition;
import mk.ecode.players.model.exceptions.InvalidPlayerIdException;
import mk.ecode.players.repository.PlayerRepository;
import mk.ecode.players.service.PlayerService;
import mk.ecode.players.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    @Override
    public List<Player> listAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(Long id) {
        return playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
    }

    @Override
    public Player create(String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        Player player = new Player();

        player.setName(name);
        player.setBio(bio);
        player.setPointsPerGame(pointsPerGame);
        player.setPosition(position);
        player.setTeam(teamService.findById(team));

        return playerRepository.save(player);
    }

    @Override
    public Player update(Long id, String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        Player player = findById(id);

        player.setName(name);
        player.setBio(bio);
        player.setPointsPerGame(pointsPerGame);
        player.setPosition(position);
        player.setTeam(teamService.findById(team));

        return playerRepository.save(player);
    }

    @Override
    public Player delete(Long id) {
        Player player = findById(id);
        playerRepository.delete(player);
        return player;
    }

    @Override
    public Player vote(Long id) {
        Player player = findById(id);

        player.setVotes(player.getVotes() + 1);
        return playerRepository.save(player);
    }

    @Override
    public List<Player> listPlayersWithPointsLessThanAndPosition(Double pointsPerGame, PlayerPosition position) {
        if (pointsPerGame != null && position != null) {
            return playerRepository.findAllByPointsPerGameLessThanAndPosition(pointsPerGame, position);
        } else if (pointsPerGame != null) {
            return playerRepository.findAllByPointsPerGameLessThan(pointsPerGame);
        } else if (position != null) {
            return playerRepository.findAllByPosition(position);
        }

        return listAllPlayers();
    }
}
