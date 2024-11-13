package com.goaaal.api.domain.port.in;

import com.goaaal.api.domain.model.Match;

import java.util.List;

public interface MatchUseCase {
    Match saveMatch(Match match);
    List<Match> getMatchesByCreator(String creatorId);
    void deleteMatchById(String id);
}
