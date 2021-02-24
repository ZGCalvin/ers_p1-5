package com.revature.servlets;

import com.revature.hibernate.Reimbursements.ReimbursementsRepository;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "EmployeeServlet",
        description= "Employee Screen",
        urlPatterns = {"/employeeServlet"}
)
public class employeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        String amount = request.getParameter("amount");
        String description = request.getParameter("description");
        String authorId = request.getParameter("authorId");
//        String status = request.getParameter("status");
        String type = request.getParameter("type");

        Reimbursement reimbursement = new Reimbursement(Double.valueOf(amount),description,
                Integer.valueOf(authorId),ReimbursementStatus.getByNumber(2), ReimbursementType.getByName(type));
        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
        reimbursementsRepository.addReimbursement(reimbursement);

        pt.println("Reimbursement string: " + reimbursementsRepository.toString());


    }


}
