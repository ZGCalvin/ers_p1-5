package com.revature.hibernate.users;

import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository {
    private Session s;

    public UserRepository(){
        s = HibernateUtil.getSessionFactory().openSession();
    }


    public int getRole(String username, String password){
        int role = 0;
        s.beginTransaction();
        String baseQuery = "FROM User ";
        String whereUserNPass = " WHERE username = :user AND password = :pass";
        Query query = s.createQuery(baseQuery + whereUserNPass).setParameter("user",username).setParameter("pass",password);
        List<User> results = query.getResultList();
        for(User u : results){
            role= u.getUserRole();
        }
        s.getTransaction().commit();
        s.close();

        return role;
    }


    public User getUser(String username, String password){
        int role = 0;
        s.beginTransaction();
        String baseQuery = "FROM User ";
        String whereUserNPass = " WHERE username = :user AND password = :pass";
        Query query = s.createQuery(baseQuery + whereUserNPass).setParameter("user",username).setParameter("pass",password);
        List<User> results = query.getResultList();
        for(User u : results){
            role= u.getUserRole();
        }
        s.getTransaction().commit();
        s.close();

        return new User();
    }
}