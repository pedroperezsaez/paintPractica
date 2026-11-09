package com.paint.paintPrimeraPractica.Respository;


import com.paint.paintPrimeraPractica.model.User;

public interface UserRepo {
   User buscarUser(String username);
   User addUser(String username, String password);

}

