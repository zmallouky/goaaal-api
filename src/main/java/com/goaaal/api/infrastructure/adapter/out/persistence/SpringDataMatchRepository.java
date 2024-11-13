package com.goaaal.api.infrastructure.adapter.out.persistence;

import com.goaaal.api.domain.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMatchRepository extends MongoRepository<Match, String> {
    List<Match> findByCreatorId(String creatorId);
}
