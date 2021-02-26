package com.revature.servlets;

import com.revature.hibernate.Reimbursements.ReimbursementsRepository;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.util.StatusHelper;
import com.revature.util.TypeHelper;
import com.revature.util.UserSession;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@WebServlet(
        name = "EmployeeServlet",
        description= "Employee Screen",
        urlPatterns = {"/employeeServlet"}
)
public class employeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        if (UserSession.getUserSession().getSession().getAttribute("role").equals(3)) {

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        if (UserSession.getUserSession().getSession().getAttribute("role").equals(3)) {

        }

    }


}
