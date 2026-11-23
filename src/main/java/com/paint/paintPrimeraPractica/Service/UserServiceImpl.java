package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.Respository.UserRepo;
import com.paint.paintPrimeraPractica.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    int contadorId = 2;

    @Override
    public User buscarUser(String username) {
        return userRepo.buscarUser(username);
    }

    @Override
    public User addUser(String username, String password) {
        if (!username.isEmpty() && password.length() > 5) {
            User user = userRepo.addUser(username, password, contadorId);
            contadorId++;

            return user;
        }

        return null;
    }

}

