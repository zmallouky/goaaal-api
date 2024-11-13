package com.goaaal.api.domain.port.out;

import com.goaaal.api.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByEmail(String email);
}
