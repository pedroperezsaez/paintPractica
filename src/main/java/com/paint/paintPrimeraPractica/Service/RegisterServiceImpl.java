package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.Respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    UserRepo userRepo;
    int contadorId=1;
    @Override
    public boolean checkRegister(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        if (userRepo.buscarUser(username) != null) {
            return false;
        }
        userRepo.addUser(username, password,contadorId);
        contadorId++;

        return true;
    }
}
