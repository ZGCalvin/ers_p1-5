package com.revature.servlets;

import com.revature.util.Session;
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
        name="dashboardServlet",
        description= "DashBoard",
        urlPatterns = {"/dashboardServlet"}
)
public class dashboard extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        //UserSession.getUserSession().getSession().getAttribute("fullname");
        pt.println("name "+ UserSession.getUserSession().getSession().getAttribute("fullname"));
    }
}
