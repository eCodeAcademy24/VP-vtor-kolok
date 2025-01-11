package mk.ecode.players.repository;

import mk.ecode.players.model.Player;
import mk.ecode.players.model.PlayerPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findAllByPointsPerGameLessThanAndPosition(Double pointsPerGame, PlayerPosition position);

    List<Player> findAllByPointsPerGameLessThan(Double pointsPerGame);

    List<Player> findAllByPosition(PlayerPosition position);
}
