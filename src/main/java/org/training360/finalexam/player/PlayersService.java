package org.training360.finalexam.player;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayersService {

    private ModelMapper modelMapper;

    private PlayersRepository repository;

    public List<PlayerDTO> getPlayerList() {
        return repository.findAll().stream()
                .map(player -> modelMapper.map(player, PlayerDTO.class))
                .toList();
    }

    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition());
        repository.save(player);
        return modelMapper.map(player, PlayerDTO.class);
    }

    public void deletePlayer(long id) {
        repository.deleteById(id);
    }
}
