package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.Respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserRepo userRepo;
    @Override
    public boolean checkRegister(String username, String password) {
        return  true;
    }
}
