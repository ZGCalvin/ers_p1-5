package com.revature.servlets;

import com.revature.hibernate.users.UserRepository;
import com.revature.hibernate.users.UserService;
import com.revature.models.Role;
import com.revature.util.UserSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name="loginServlet",
        description= "Login Screen",
        urlPatterns = {"/loginServlet"}
)
public class loginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter pt = response.getWriter();
        UserService userService;
        String user = request.getParameter("username");
        String pass = request.getParameter("password");


        int role;

        userService = new UserService();
        UserRepository userRepository = new UserRepository();
        role = userService.authenticateRole(user,pass);


        HttpSession session = UserSession.getUserSession().createSession(request,userService.authenticate(user,pass));

        if(role == Role.ADMIN.ordinal()+1){
            pt.println("Session User: "+ session.getAttribute("fullname"));
//            response.sendRedirect("/ERS1.5/adminServlet");

        }
        else if(role == Role.FINANCE_MANAGER.ordinal()+1){
            pt.println("Session User: "+ session.getAttribute("fullname"));
            pt.println("Finance Manager");
        }
        else if(role == Role.EMPLOYEE.ordinal()+1){
            pt.println("Session User: "+ session.getAttribute("fullname"));
            pt.println("Employee");
        }
        else{
            pt.println("Not a valid User");
            response.setStatus(401);
        }

        pt.println("Login Screen");

    }

}