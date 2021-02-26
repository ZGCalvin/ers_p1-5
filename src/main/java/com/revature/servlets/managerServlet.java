package com.revature.servlets;


import com.revature.hibernate.Reimbursements.ReimbursementService;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.util.StatusHelper;
import com.revature.util.TypeHelper;
import com.revature.util.UserSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
            ReimbursementService reimService = new ReimbursementService();
            StatusHelper statusHelper = new StatusHelper();
            TypeHelper typeHelper = new TypeHelper();
            Integer integer;
            List results;
            switch (method) {
                case "ViewAll":
                    pt.println("All Reimbursements :"  +reimService.viewAllReimbursement());
                    break;
                case "ViewAllByStatus":
                    String status = request.getParameter("status");
                    integer = ReimbursementStatus.getByName(status).ordinal()+1;
                    results = reimService.viewAllReimbursementByStatus(statusHelper.convertToEntityAttribute(integer));
                    pt.println("View All Reimbursements By Status: " +results);
                    break;
                case "ViewAllByType":
                    String type = request.getParameter("type");
                    integer = ReimbursementType.getByName(type).ordinal()+1;
                    results = reimService.viewAllReimbursementByType(typeHelper.convertToEntityAttribute(integer));
                    pt.println("View All Reimbursements By Type: " +results);
                    break;
                default:
                    response.setStatus(404);

            }
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        String method = request.getParameter("method");

        if (UserSession.getUserSession().getSession().getAttribute("role").equals(2)) {
            ReimbursementService reimService = new ReimbursementService();
            switch (method) {
                case "Add":
                    //reimService.addReimbursement( );
                    break;
                case "UpdateResolver":
                    //reimService.updateResolverByReimbursementId();
                    break;
                case "UpdateStatusTime":
                    //reimService.updateStatusTime()
                    break;
                default:
                    response.setStatus(404);
            }

        }
    }
}
