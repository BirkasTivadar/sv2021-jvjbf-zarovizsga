package org.training360.finalexam.teams;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.player.PlayerDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    private TeamsService teamsService;

    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @GetMapping
    public List<TeamDTO> getTeamList() {
        return teamsService.getTeamList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createTeam(@Valid @RequestBody CreateTeamCommand command){
        return teamsService.createTeam(command);
    }

    @PostMapping("{id}/players")
    public PlayerDTO addNewPlayer(@PathVariable("id") long id, @Valid @RequestBody AddNewPlayerCommand command){
        return teamsService.addNewPlayer(id, command);
    }

    @PutMapping("{id}/players")
    public void addExistingPlayer(@PathVariable("id") long id, UpdateWithExistingPlayerCommand command){
        teamsService.addExistingPlayer(id, command);
    }
}
