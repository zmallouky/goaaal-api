package com.goaaal.api.infrastructure.adapter.in.web;

import com.goaaal.api.domain.model.User;
import com.goaaal.api.domain.port.in.UserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserUseCase userService;

    public UserController(UserUseCase userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        User newUser = userService.signup(user.getEmail(), user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String token = userService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(token);
    }
}