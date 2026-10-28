package com.example.primeraPractica.Respository;


import com.example.primeraPractica.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepoImpl implements UserRepo {
    private final List<User> users = new ArrayList<>();



    @Override
    public User buscarUser(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public User addUser(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            User user = new User(username, password);
            users.add(user);

            return user;

        }


        return null;
    }
}
