package com.example.primeraPractica.Respository;


import com.example.primeraPractica.model.User;

public interface UserRepo {
   User buscarUser(String username);
   User addUser(String username, String password);

}

