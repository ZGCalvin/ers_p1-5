package com.revature.servlets;

import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(
        name="loginServlet",
        description= "Login Screen",
        urlPatterns = {"/loginServlet"}
)
public class loginServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter pt = response.getWriter();
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();

        String baseQuery = "SELECT * FROM ers_users eu ";
        String whereUserNPass = "WHERE username = :" + userId + " AND  password = :"+password+ " ";
        Query query = s.createQuery(baseQuery);
        List results = query.list();
        s.getTransaction().commit();
        s.close();

//        if(userId.equals("123"))
//            response.sendRedirect("https://www.youtube.com");

        pt.println("Login Screen");
        pt.println("user : " + results.toString());
    }

}