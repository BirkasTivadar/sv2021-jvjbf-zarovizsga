package org.training360.finalexam.player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.training360.finalexam.teams.Team;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_pos")
    private PositionType position;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Team team;

    public Player(String name, LocalDate birthDate, PositionType position) {
        this.name = name;
        this.birthDate = birthDate;
        this.position = position;
    }

//        public String getPosition() {
//        return this.position.name();
//    }
//
//    public void setPosition(String pos){
//        this.position = PositionType.valueOf(pos);
//    }
}
