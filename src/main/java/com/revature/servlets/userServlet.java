package com.revature.servlets;

import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name="userServlet",
        description= "User Login",
        urlPatterns = {"/userServlet"}
)

public class userServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();


        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        User newUsers = new User(10,"calvin3483032","calvin123","calvin","zheng","calvin802138021@yahoo.com",1);
        s.save(newUsers);
        s.getTransaction().commit();
        s.close();
        out.println("User Servlet");
    }

}
