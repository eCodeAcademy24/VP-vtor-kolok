package mk.ecode.players.config;

import jakarta.annotation.PostConstruct;
import mk.ecode.players.model.PlayerPosition;
import mk.ecode.players.service.TeamService;
import mk.ecode.players.service.PlayerService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final TeamService teamService;

    private final PlayerService playerService;

    public DataInitializer(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    private PlayerPosition randomizePosition(int i) {
        if(i % 3 == 0) return PlayerPosition.G;
        else if(i % 3 == 1) return PlayerPosition.F;
        return PlayerPosition.C;
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 6; i++) {
            this.teamService.create("Team: " + i);
        }

        for (int i = 1; i < 11; i++) {
            this.playerService.create("Player: " + i, "Bio: " + i , 20.9 * i, this.randomizePosition(i), this.teamService.listAll().get((i-1)%5).getId());
        }
    }
}
