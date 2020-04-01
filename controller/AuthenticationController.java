package controller;

import classe.User;
import model.AuthenticationModel;

import java.security.NoSuchAlgorithmException;

public class AuthenticationController {
    User errorUser = new User(0, "Error", "Error", "Error", "Error",false);
    public AuthenticationController() {
        super();
    }

    public User checkAuth(String login, String password) {
        AuthenticationModel auth = new AuthenticationModel();
        User user;
        try {
            user = auth.checkAuth(login, password);
            if(user.getId() != 0){
                return user;
            } else {
                return errorUser;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return errorUser;
        }
    }

    public void createAuth(User u) {
        AuthenticationModel auth = new AuthenticationModel();
        auth.createAuth(u);
    }
}
