package org.training360.finalexam.player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.training360.finalexam.teams.TeamDTO;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private PositionType position;

    @JsonBackReference
    private TeamDTO team;
}
