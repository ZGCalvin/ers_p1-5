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
        name="registerServlet",
        description= "Register",
        urlPatterns = {"/registerServlet"}
)
public class registerServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter pt = response.getWriter();
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String fname = request.getParameter("username");
        String lname = request.getParameter("password");
        String email = request.getParameter("username");
        User newUser = new User(user,pass,fname,lname,email,1);
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(newUser);
        s.getTransaction().commit();
        s.close();

        String htmlRespone = "<html>";
        htmlRespone += "<a href='http://localhost:8080/ERS1.5/'>HOME</a>";
        htmlRespone += "<h2>user : user registered </h2><br/>";
        htmlRespone += "</html>";

        pt.println(htmlRespone);

    }
}