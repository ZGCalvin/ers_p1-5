package com.revature.servlets;

import com.revature.hibernate.Reimbursements.ReimbursementsRepository;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.util.StatusHelper;
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
        name = "EmployeeServlet",
        description= "Employee Screen",
        urlPatterns = {"/employeeServlet"}
)
public class employeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        String status = request.getParameter("status");
//        String amount = request.getParameter("amount");
//        String description = request.getParameter("description");
//        String authorId = request.getParameter("authorId");
//        String status = request.getParameter("status");
//        String type = request.getParameter("type");

        //Insert into reimbursement
//        Reimbursement reimbursement = new Reimbursement(
//                Double.valueOf(amount),//amount
//                description,//description
//                Integer.parseInt(authorId),//author ID
//                ReimbursementStatus.getByNumber(2),//reimbursement status
//                ReimbursementType.getByName(type));// reimbursement type
//        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
//        reimbursementsRepository.addReimbursement(reimbursement);
//
//
//
//
//        pt.println("Reimbursement string: " + reimbursementsRepository.toString());

        //view ALL reimbursements
//        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
//        List results = reimbursementsRepository.viewAllReimbursement();
//        pt.println("List of Reimbursements: " + results.toString());

        //view ALL reimbursements by Status
        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
        StatusHelper statusHelper = new StatusHelper();
//        List results = reimbursementsRepository.viewAllReimbursementbyStatus();
        Integer integer = ReimbursementStatus.getByName(status).ordinal()+1;
        List results = reimbursementsRepository.viewAllReimbursementbyStatus(statusHelper.convertToEntityAttribute(integer));
//        List results = reimbursementsRepository.viewAllReimbursementbyStatus(ReimbursementStatus.getByName(status));
        pt.println("List of Reimbursements: " + results.toString());



    }


}
