package com.revature.servlets;


import com.revature.util.UserSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name="mangerServlet",
        description= "manager page",
        urlPatterns = {"/managerServlet"}
)
public class managerServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        String method = request.getParameter("method");

        if (UserSession.getUserSession().getSession().getAttribute("role").equals(2)) {

        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        String method = request.getParameter("method");

        if (UserSession.getUserSession().getSession().getAttribute("role").equals(2)) {

        }

    }
}
