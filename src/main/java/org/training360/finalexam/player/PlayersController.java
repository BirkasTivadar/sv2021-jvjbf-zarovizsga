package org.training360.finalexam.player;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayersController {

    private PlayersService playersService;

    public PlayersController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping
    public List<PlayerDTO> getPlayerList() {
        return playersService.getPlayerList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDTO createPlayer(@Valid @RequestBody CreatePlayerCommand command) {
        return playersService.createPlayer(command);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayer(@PathVariable("id") long id) {
        playersService.deletePlayer(id);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Problem> handleNotFound(IllegalStateException exception) {
        Problem problem = Problem.builder()
                .withType(URI.create("player/invalid-name"))
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
