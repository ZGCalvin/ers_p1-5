package com.revature.hibernate.users;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 *  adds the functionality to get the Role, get the user, add a user, delete a user, and update fields
 *  for the user such as the password, email, etc.
 */
public class UserRepository {
    private Session s;

    public UserRepository(){
        s = HibernateUtil.getSessionFactory().openSession();
    }


    /**
     *
     * @param username user credential used in a where clause to find a specific record
     * @param password user credential used in a where clause to find a specific record
     * @return an int that represents the role recieved depending on the username and password given
     */
    public int getRole(String username, String password){
        int role = 0;
        try {
            s.beginTransaction();
            String baseQuery = "FROM User ";
            String whereUserNPass = " WHERE username = :user AND password = :pass";
            Query query = s.createQuery(baseQuery + whereUserNPass).setParameter("user", username).setParameter("pass", password);
            List<User> results = query.getResultList();
            for (User u : results) {
                role = u.getUserRole();
            }

            s.getTransaction().commit();
            s.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return role;
    }


    /**
     *
     * @param username user credential used in a where clause to find a specific record
     * @param password user credential used in a where clause to find a specific record
     * @return gives back the whole user that corresponds to the credentials given
     */
    public User getUser(String username, String password){
        int role = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = new User();
        try {
            session.beginTransaction();
            String baseQuery = "FROM User ";
            String whereUserNPass = " WHERE username = :user AND password = :pass";
            Query query = session.createQuery(baseQuery + whereUserNPass).setParameter("user", username).setParameter("pass", password);
            List<User> results = query.getResultList();
            for (User u : results) {
                user.setUserId(u.getUserId());
                user.setUsername(u.getUsername());
                user.setPassword(u.getPassword());
                user.setFirstname(u.getFirstname());
                user.setLastname(u.getLastname());
                user.setUserRole(u.getUserRole());
            }
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     *
     * @param user takes in the user object given and saves it to the database
     */
    public void addUser(User user){
        try {
            s.beginTransaction();
            s.save(user);
            s.getTransaction().commit();
            s.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id  find the user associated with the id passed in and delete that user
     * @return a true or false is given based on if the user was "deleted" or not
     */
    public boolean deleteUser(Integer id){

            boolean updated = false;

           User user;
            try {
                    s.beginTransaction();
                    user = s.get(User.class, id);
                    s.delete(user);
                    s.getTransaction().commit();
                    updated = true;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            s.close();
            return updated;

        }

    /**
     *
     * @param id used to get the user associated with the the id from the session
     * @param password holds the value that changes the database password will be changed to
     * @return returns a boolean on if the field was updated
     */
    public boolean updatePassword(Integer id,String password){

        boolean updated = false;
        User user;
        try {
            s.beginTransaction();
            user = s.get(User.class, id);
            user.setPassword(password);
            s.save(user);
            s.getTransaction().commit();
            updated = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        s.close();
        return updated;

    }

    /**
     *
     * @param id used to get the user associated with the the id from the session
     * @param email the information in the parameter will be the new information in the database for email
     * @return returns a boolean on if the field was updated
     */
    public boolean updateEmail(Integer id,String email){

        boolean updated = false;
        User user;
        try {
            s.beginTransaction();
            user = s.get(User.class, id);
            user.setEmail(email);
            s.save(user);
            s.getTransaction().commit();
            updated = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        s.close();
        return updated;

    }

    /**
     *
     * @param id used to get the user associated with the the id from the session
     * @param first the parameter holds the first name that will be updating the first name field in the database
     * @param last the parameter holds the last name that will be updating the last name field in the database
     * @return returns a boolean on if the field was updated
     */
    public boolean updateFirstLast(Integer id,String first,String last){

        boolean updated = false;
        User user;
        try {
            s.beginTransaction();
            user = s.get(User.class, id);
            user.setFirstname(first);
            user.setLastname(last);
            s.save(user);
            s.getTransaction().commit();
            updated = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        s.close();
        return updated;

    }

    /**
     *
     * @param id used to get the user associated with the the id from the session
     * @param role integer representation of the role to be used to update the role in the database
     * @return returns a boolean on if the field was updated
     */
    public boolean updateStatus(Integer id,Integer role){

        boolean updated = false;
        User user;
        try {
            s.beginTransaction();
            user = s.get(User.class, id);
            user.setUserRole(role);
            s.save(user);
            s.getTransaction().commit();
            updated = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        s.close();
        return updated;

    }

    /**
     *
     * @return returns a List of all the users
     */
    public List viewAllUsers(){
        List results = null;
        try {
            s.beginTransaction();
            Query query = s.createQuery("From User");
            results = query.getResultList();
            s.getTransaction().commit();
            s.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }


}
