package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.finalexam.player.CreatePlayerCommand;
import org.training360.finalexam.player.Player;
import org.training360.finalexam.player.PlayerDTO;
import org.training360.finalexam.player.PlayersRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamsService {

    private ModelMapper modelMapper;

    private TeamsRepository repository;

    private PlayersRepository playersRepository;

    public List<TeamDTO> getTeamList() {
        return repository.findAll().stream()
                .map(team -> modelMapper.map(team, TeamDTO.class))
                .toList();
    }

    public TeamDTO createTeam(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        repository.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public PlayerDTO addNewPlayer(long id, CreatePlayerCommand command) {
        Team team = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found team with id: " + id));
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition());
//        playersRepository.save(player);
        team.addPlayer(player);
        return modelMapper.map(player, PlayerDTO.class);
    }

    @Transactional
    public void addExistingPlayer(long id, UpdateWithExistingPlayerCommand command) {
        Team team = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found team with id: " + id));
        Player player = repository.findPlayerById(command.getPlayerId());
        if (checkEligiblePlayer(team, player)) {
            team.addPlayer(player);
        }
    }

    private boolean checkEligiblePlayer(Team team, Player player) {
        int number = (int) team.getPlayers().stream()
                .filter(p -> p.getPosition() == player.getPosition()).count();
        return (player.getTeam() == null) && (number < 2);


    }
}
