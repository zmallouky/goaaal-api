package com.goaaal.api.application.service;

import com.goaaal.api.config.JwtTokenProvider;
import com.goaaal.api.domain.model.User;
import com.goaaal.api.domain.port.in.UserUseCase;
import com.goaaal.api.domain.port.out.UserRepositoryPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserUseCase {

    private final UserRepositoryPort userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepositoryPort userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User signup(String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            // Génération du token JWT
            User user = userOpt.get();
            return jwtTokenProvider.createToken(user.getId(), user.getEmail(), List.of("ROLE_USER"));
        }
        throw new RuntimeException("Invalid credentials");
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
