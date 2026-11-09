package com.paint.paintPrimeraPractica.Respository;


import com.paint.paintPrimeraPractica.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepoImpl implements UserRepo {
    private final List<User> users = new ArrayList<>();
    UserRepoImpl(){
        users.add(new User("pedro","1", 1));
        users.add(new User("juan","1" ,1234));
    }



    @Override
    public User buscarUser(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public User addUser(String username, String password,int id) {

        User user = new User(username, password, id);
        users.add(user);


        return user;


    }
}
