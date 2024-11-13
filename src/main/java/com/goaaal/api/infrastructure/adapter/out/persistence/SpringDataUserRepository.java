package com.goaaal.api.infrastructure.adapter.out.persistence;

import com.goaaal.api.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
