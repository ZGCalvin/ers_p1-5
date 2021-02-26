package com.revature.hibernate.users;

import com.revature.exceptions.InvalidRequestException;
import com.revature.models.User;

import java.util.List;

/**
 *  does authentication and also acts as an inbetween for the UserRepository since it called the methods
 *  and returns their outputs
 */
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

    /**
     *
     * @param username credentials to authenticate then find the user that has it
     * @param password credentials to authenticate then find the user that has it
     * @return a user recieved from the database which has the username and password passed in
     */
    public User authenticate(String username, String password){
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")){
            throw new RuntimeException("Invalid credentials provided");
        }

        return userRepo.getUser(username,password);
    }

    /**
     *
     * @param user takes in the user passed in and call the userRepo addUser to save to the database
     */
    public void AddUser(User user){
        if (!isUserValid(user)) throw new InvalidRequestException();
        userRepo.addUser(user);

    }

    /**
     *
     * @param id  id of the user to find specific user
     * @return returns true or false if the user with the passed in id got "deleted"
     */
    public boolean deleteUser(Integer id){
        if (id <= 0) {throw new InvalidRequestException();}
        return userRepo.deleteUser(id);
    }

    /**
     *
     * @param id id of the user
     * @param password new password to change in the database
     * @return returns true or false if the value passed in got updated into the database
     */
    public boolean updatePassword(Integer id,String password){
        if (!isStringValid(password)) throw new InvalidRequestException();
        return userRepo.updatePassword(id,password);

    }

    /**
     *
     * @param id id of the user
     * @param email new email to change in the database
     * @return returns true or false if the value passed in got updated into the database
     */
    public boolean updateEmail(Integer id, String email){
        if (!isStringValid(email)) throw new InvalidRequestException();
        return userRepo.updateEmail(id,email);
    }

    /**
     *
     * @param id id of the user
     * @param first new first name to change in the database
     * @param last new last name to change in the database
     * @return returns true or false if the value's passed in got updated into the database
     */
    public boolean updateFirstLast(Integer id, String first,String last){
        if (!isStringValid(first, last)) throw new InvalidRequestException();
        return userRepo.updateFirstLast(id,first,last);
    }

    /**
     *
     * @param id id of the user
     * @param role the new role to be put into the database
     * @return returns true or false if the value passed in got updated into the database
     */
    public boolean updateRole(Integer id,Integer role){
        if (!isRoleValid(role)) throw new InvalidRequestException();
        return userRepo.updateStatus(id,role);
    }

    /**
     *
     * @return a List of all the Users recived from calling the viewAllUsers() method in the UserRepository class
     */
    public List viewAllUsers(){
        return userRepo.viewAllUsers();
    }


    /**
     *
     * @param user passed in the user object
     * @return if any of the fields of the user object are invalid information returns false
     */
    public Boolean isUserValid(User user) {
        if (user == null) return false;
        if (user.getFirstname() == null || user.getFirstname().trim().equals("")) return false;
        if (user.getLastname() == null || user.getLastname().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        return true;
    }

    /**
     *
     * @param s1 the string to check
     * @return returns whether the string passed in is valid
     */
    public Boolean isStringValid(String s1) {
        if (s1 == null || s1.trim().equals("")) return false;
        return true;
    }

    /**
     *
     * @param s1 string to check if valid
     * @param s2 string to check if valid
     * @return if the string's passed in are both valid
     */
    public Boolean isStringValid(String s1, String s2) {
        if (s1 == null || s1.trim().equals("")) return false;
        if (s2 == null || s2.trim().equals("")) return false;
        return true;
    }

    /**
     *
     * @param i the integer representation of a role
     * @return if the role passed in is a valid role
     */
    public Boolean isRoleValid(int i) {
        if (i < 1 || i > 4) return false;
        return true;
    }


}
