package com.goaaal.api.application.service;

import com.goaaal.api.domain.model.Match;
import com.goaaal.api.domain.port.in.MatchUseCase;
import com.goaaal.api.domain.port.out.MatchRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService implements MatchUseCase {

    private final MatchRepositoryPort matchRepository;

    public MatchService(MatchRepositoryPort matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public List<Match> getMatchesByCreator(String creatorId) {
        return matchRepository.findByCreatorId(creatorId);
    }

    @Override
    public void deleteMatchById(String id) {
        matchRepository.deleteById(id);
    }
}
