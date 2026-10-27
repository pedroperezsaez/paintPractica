package com.example.primeraPractica.Respository;


import com.example.primeraPractica.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepoImpl implements UserRepo{

    @Override
    public User buscarUser(String username) {
        if(username.equals("pedro")){
            return  new User("pedro","1234");
        }else
            return  new User("bilfl","12348");
    }


}
