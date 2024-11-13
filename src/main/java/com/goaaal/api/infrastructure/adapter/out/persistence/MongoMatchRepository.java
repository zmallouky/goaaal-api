package com.goaaal.api.infrastructure.adapter.out.persistence;


import com.goaaal.api.domain.model.Match;
import com.goaaal.api.domain.port.out.MatchRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoMatchRepository implements MatchRepositoryPort {

    private final SpringDataMatchRepository matchRepository;

    @Autowired
    public MongoMatchRepository(SpringDataMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match save(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public List<Match> findByCreatorId(String creatorId) {
        return matchRepository.findByCreatorId(creatorId);
    }

    @Override
    public void deleteById(String id) {
        matchRepository.deleteById(id);
    }
}
