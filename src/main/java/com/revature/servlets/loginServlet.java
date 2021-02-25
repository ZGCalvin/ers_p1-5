package com.revature.servlets;

import com.revature.hibernate.users.UserRepository;
import com.revature.hibernate.users.UserService;
import com.revature.models.Role;
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


        int role;
        UserService userService = new UserService();
        role = userService.authenticateRole(user,pass);

        if(role == Role.ADMIN.ordinal()+1){
            pt.println("Admin");
        }
        else if(role == Role.FINANCE_MANAGER.ordinal()+1){
            pt.println("Finance Manager");
        }
        else if(role == Role.EMPLOYEE.ordinal()+1){
            pt.println("Employee");
        }
        else{
            pt.println("Not a valid User");
        }
        pt.println("Login Screen");
        pt.println("user role number : " + role);

    }

}