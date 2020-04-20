package controller;

import classe.User;
import model.AuthenticationModel;

import java.security.NoSuchAlgorithmException;

public class AuthenticationController {
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
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createAuth(User u) {
        AuthenticationModel auth = new AuthenticationModel();
        auth.createAuth(u);
    }
}
