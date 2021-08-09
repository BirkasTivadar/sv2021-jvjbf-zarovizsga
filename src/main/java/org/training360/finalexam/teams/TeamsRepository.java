package org.training360.finalexam.teams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.training360.finalexam.player.Player;

public interface TeamsRepository extends JpaRepository<Team, Long> {

    @Query("select p from Player p where p.id = :id")
    Player findPlayerById(Long id);

}
