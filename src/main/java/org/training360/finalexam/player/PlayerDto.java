package org.training360.finalexam.player;

import lombok.*;
import org.training360.finalexam.team.TeamDto;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private PositionType position;

    private TeamDto team;
}
