package Respository;

import model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepoImpl implements UserRepo {
private final List<User> users;
public  UserRepoImpl(List<User> users){
    this.users=users;
}
    @Override
    public User buscarUser(String username) {
      /*  for (User user:users){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;

       */

       return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);


    }
}
