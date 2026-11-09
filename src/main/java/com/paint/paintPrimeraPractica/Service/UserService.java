package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.model.User;

public interface UserService {
        User buscarUser(String username);
        User addUser(String username, String password);
}
