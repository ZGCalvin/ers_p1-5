package com.revature.hibernate.users;

import com.revature.exceptions.InvalidRequestException;
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
        if (!isUserValid(user)) throw new InvalidRequestException();
        userRepo.addUser(user);

    }

    public boolean deleteUser(Integer id){
        if (id <= 0) {throw new InvalidRequestException();}
        return userRepo.deleteUser(id);
    }

    public boolean updatePassword(Integer id,String password){
        if (!isStringValid(password)) throw new InvalidRequestException();
        return userRepo.updatePassword(id,password);

    }

    public boolean updateEmail(Integer id, String email){
        if (!isStringValid(email)) throw new InvalidRequestException();
        return userRepo.updateEmail(id,email);
    }

    public boolean updateFirstLast(Integer id, String first,String last){
        if (!isStringValid(first, last)) throw new InvalidRequestException();
        return userRepo.updateFirstLast(id,first,last);
    }

    public boolean updateRole(Integer id,Integer role){
        if (!isRoleValid(role)) throw new InvalidRequestException();
        return userRepo.updateStatus(id,role);
    }

    public List viewAllUsers(){
        return userRepo.viewAllUsers();
    }



    public Boolean isUserValid(User user) {
        if (user == null) return false;
        if (user.getFirstname() == null || user.getFirstname().trim().equals("")) return false;
        if (user.getLastname() == null || user.getLastname().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        return true;
    }

    public Boolean isStringValid(String s1) {
        if (s1 == null || s1.trim().equals("")) return false;
        return true;
    }

    public Boolean isStringValid(String s1, String s2) {
        if (s1 == null || s1.trim().equals("")) return false;
        if (s2 == null || s2.trim().equals("")) return false;
        return true;
    }

    public Boolean isRoleValid(int i) {
        if (i < 1 || i > 4) return false;
        return true;
    }


}
