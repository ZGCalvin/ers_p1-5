package com.revature.servlets;


import com.revature.hibernate.Reimbursements.ReimbursementService;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.util.PrintSelect;
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
import java.sql.Timestamp;
import java.util.List;

@WebServlet(
        name="managerServlet",
        description= "manager page",
        urlPatterns = {"/managerServlet"}
)

/**
 *  manager has a doGet and a doPost
 *  doGet can view all reimbursements, and can view them by their status or their type
 */
public class managerServlet extends HttpServlet {

    private PrintSelect printSelect = new PrintSelect();

    /**
     *
     * @param request gets the information from the user
     * @param response can give something back to the user for example a status code
     * @throws IOException error if something goes wrong with writting or getting information
     * @throws ServletException error if something goes wrong with the servlet
     * */
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
                    pt.println("All Reimbursements :");
                    printSelect.printRowWithOut(reimService.viewAllReimbursement(),pt);
                    break;
                case "ViewAllByStatus":
                    String status = request.getParameter("status");
                    integer = ReimbursementStatus.getByName(status).ordinal()+1;
                    results = reimService.viewAllReimbursementByStatus(statusHelper.convertToEntityAttribute(integer));
                    pt.println("View All Reimbursements By Status: ");
                    printSelect.printRowWithOut(results,pt);
                    break;
                case "ViewAllByType":
                    String type = request.getParameter("type");
                    integer = ReimbursementType.getByName(type).ordinal()+1;
                    results = reimService.viewAllReimbursementByType(typeHelper.convertToEntityAttribute(integer));
                    pt.println("View All Reimbursements By Type: " );
                    printSelect.printRowWithOut(results,pt);
                    break;
                default:
                    response.setStatus(404);

            }
        }
        else{
            response.setStatus(401);
        }



    }

    /**
     *
     * @param request gets the information from the user
     * @param response can give something back to the user for example a status code
     * @throws IOException error if something goes wrong with writting or getting information
     * @throws ServletException error if something goes wrong with the servlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        String method = request.getParameter("method");

        if (UserSession.getUserSession().getSession().getAttribute("role").equals(2)) {
            ReimbursementService reimService = new ReimbursementService();
            StatusHelper statusHelper = new StatusHelper();
            boolean updated;
            Integer integer;
            String reimbursementid;
            String resolverid;

            switch (method) {

                case "UpdateResolver":
                    reimbursementid = request.getParameter("Rid");
                    //resolverid = request.getParameter("Myid");
                    updated = reimService.updateResolverByReimbursementId(Integer.valueOf(reimbursementid),(Integer) UserSession.getUserSession().getSession().getAttribute("user_id"));
                    if(updated){
                    pt.println("Updated Resolver");
                    }
                    else{
                    pt.println("Not updated");
                    }
                    break;
                case "UpdateStatusTime":
                    reimbursementid = request.getParameter("Rid");
                    String status = request.getParameter("status");
                    integer = ReimbursementStatus.getByName(status).ordinal()+1;
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    updated = reimService.updateStatusTime(Integer.valueOf(reimbursementid), timestamp,statusHelper.convertToEntityAttribute(integer));
                    if(updated){
                        pt.println("Updated Status and Time");
                    }
                    else{
                        pt.println("Not updated");
                    }
                    break;
                default:
                    response.setStatus(404);
            }

        }
        else{
            response.setStatus(401);
        }
    }
}
