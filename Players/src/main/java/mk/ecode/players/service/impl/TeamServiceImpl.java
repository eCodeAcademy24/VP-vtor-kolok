package mk.ecode.players.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.players.model.Team;
import mk.ecode.players.model.exceptions.InvalidTeamIdException;
import mk.ecode.players.repository.TeamRepository;
import mk.ecode.players.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(InvalidTeamIdException::new);
    }

    @Override
    public List<Team> listAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team create(String name) {
        Team team = new Team();

        team.setName(name);

        return teamRepository.save(team);
    }
}
