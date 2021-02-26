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

    public void AddUser(User user){

         userRepo.addUser(user);

    }

    public boolean deleteUser(Integer id){
        return userRepo.deleteUser(id);
    }

    public boolean updatePassword(Integer id,String password){

        return userRepo.updatePassword(id,password);

    }

    public boolean updateEmail(Integer id, String email){
        return userRepo.updateEmail(id,email);
    }

    public boolean updateFirstLast(Integer id, String first,String last){
        return userRepo.updateFirstLast(id,first,last);
    }

    public boolean updateRole(Integer id,Integer role){
        return userRepo.updateStatus(id,role);
    }




}
