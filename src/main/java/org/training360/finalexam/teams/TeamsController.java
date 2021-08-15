package org.training360.finalexam.teams;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.player.CreatePlayerCommand;
import org.training360.finalexam.player.PlayerDTO;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
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
    public PlayerDTO addNewPlayer(@PathVariable("id") long id, @ Valid @RequestBody CreatePlayerCommand command){
        return teamsService.addNewPlayer(id, command);
    }

    @PutMapping("{id}/players")
    public void addExistingPlayer(@PathVariable("id") long id, @RequestBody UpdateWithExistingPlayerCommand command){
        teamsService.addExistingPlayer(id, command);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException exception) {
        Problem problem = Problem.builder()
                .withType(URI.create("teams/not-found"))
                .withTitle("Not Found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(exception.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Problem> handleNotFound(IllegalStateException exception) {
        Problem problem = Problem.builder()
                .withType(URI.create("team/invalid-name"))
                .withTitle("Invalid Name")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(exception.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
