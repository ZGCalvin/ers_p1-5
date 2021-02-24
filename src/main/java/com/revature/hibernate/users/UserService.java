package com.revature.hibernate.users;

import com.revature.models.User;

import java.util.List;

public class UserService {

    UserRepository userRepo = new UserRepository();
    /**
     * Authentication method used by the authentication servlet
     * @param username username of the user
     * @param password password of the user
     * @return the object of the requested user
     */
    public int authenticateRole(String username, String password){
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")){
            throw new RuntimeException("Invalid credentials provided");
        }
        return userRepo.getRole(username,password);
    }

    public User authenticate(String username, String password){
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")){
            throw new RuntimeException("Invalid credentials provided");
        }
        return userRepo.getUser(username,password);
    }


}
