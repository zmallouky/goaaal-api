package com.goaaal.api.infrastructure.adapter.in.web;

import com.goaaal.api.domain.model.Match;
import com.goaaal.api.domain.port.in.MatchUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchUseCase matchService;

    public MatchController(MatchUseCase matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/create")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        Match newMatch = matchService.saveMatch(match);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMatch);
    }

    @GetMapping("/byCreator/{creatorId}")
    public ResponseEntity<List<Match>> getMatchesByCreator(@PathVariable String creatorId) {
        List<Match> matches = matchService.getMatchesByCreator(creatorId);
        return ResponseEntity.ok(matches);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable String id) {
        matchService.deleteMatchById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
