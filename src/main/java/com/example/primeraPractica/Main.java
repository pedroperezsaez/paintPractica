package com.example.primeraPractica;



import com.example.primeraPractica.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {
        User user=new User("Pedro","1234");
        User user1=new User("Juan","4321");
        List<User> usuarios=new ArrayList<>();
        usuarios.add(user);
        usuarios.add(user1);
    }
}
