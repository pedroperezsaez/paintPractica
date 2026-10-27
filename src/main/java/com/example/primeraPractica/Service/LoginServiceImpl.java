package Service;

import Respository.UserRepo;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    UserRepo userRepo;

    @Override
    public boolean checkLogin(String username, String password) {
        User user= userRepo.buscarUser(username);
        if (user==null) return false;
        return (password.equals(user.getPassword()));
    }
}
