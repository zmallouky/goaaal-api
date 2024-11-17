package com.goaaal.api.infrastructure.adapter.out.persistence;

import com.goaaal.api.domain.model.User;
import com.goaaal.api.domain.port.out.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoUserRepository implements UserRepositoryPort {

    private final SpringDataUserRepository userRepository;

    @Autowired
    public MongoUserRepository(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
