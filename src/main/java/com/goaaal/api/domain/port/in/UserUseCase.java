package com.goaaal.api.domain.port.in;

import com.goaaal.api.domain.model.User;

import java.util.List;

public interface UserUseCase {
    User signup(String email, String password);
    String login(String email, String password);
    List<User> getUsers();
}
