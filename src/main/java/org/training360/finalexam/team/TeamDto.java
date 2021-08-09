package org.training360.finalexam.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.player.PlayerDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private Long id;

    private String name;

    private List<PlayerDto> players;
}
