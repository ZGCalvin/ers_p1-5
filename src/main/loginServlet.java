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
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();


        String hql = "from User u where u.username =:user AND  password =:pass";
        Query query = s.createQuery(hql)
                .setParameter("user", user)
                .setParameter("pass", pass);
        List results = query.list();
        s.getTransaction();
        s.close();

//        if(userId.equals("123"))
//            response.sendRedirect("https://www.youtube.com");
        String htmlRespone = "<html>";
        htmlRespone += "<h2>user : " + results.toString()+"</h2><br/>";
        htmlRespone += "<a href='http://localhost:8080/ERS1.5/'>HOME</a>";
        htmlRespone += "</html>";

        pt.println(htmlRespone);
    }

}