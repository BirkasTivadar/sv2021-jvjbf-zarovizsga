package org.training360.finalexam.team;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamsService {

    private ModelMapper modelMapper;

    private TeamsRepository repository;

    public List<TeamDto> getTeamList() {
        return repository.findAll().stream()
                .map(team -> modelMapper.map(team, TeamDto.class))
                .toList();
    }
}
