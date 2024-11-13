package com.goaaal.api.domain.port.out;

import com.goaaal.api.domain.model.Match;

import java.util.List;

public interface MatchRepositoryPort {
    Match save(Match match);
    List<Match> findByCreatorId(String creatorId);
    void deleteById(String id);
}
