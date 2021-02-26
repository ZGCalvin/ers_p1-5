package com.revature.servlets;

import com.revature.hibernate.Reimbursements.ReimbursementService;
import com.revature.hibernate.Reimbursements.ReimbursementsRepository;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.util.PrintSelect;
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

/**
 *  Employee has a doGet and doPost
 *  doGet can allow employee to view all their reimbursments and view a specific reimbursment by their id
 *  doPost can allow employee to update fields of the reimbursement if the status is still pending
 */
public class employeeServlet extends HttpServlet {

    private PrintSelect printSelect = new PrintSelect();

    /**
     *
     * @param request gets the information from the user
     * @param response can give something back to the user for example a status code
     * @throws IOException error if something goes wrong with writting or getting information
     * @throws ServletException error if something goes wrong with the servlet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        String method = request.getParameter("method");


        if (UserSession.getUserSession().getSession().getAttribute("role").equals(3)) {
            ReimbursementService reimService = new ReimbursementService();
            List results;
            List<Reimbursement> reimList;
            String reimId;
            switch (method) {

                case "ViewMyReim":

                    results = reimService.viewAllReimbursementEmployee((Integer) UserSession.getUserSession().getSession().getAttribute("user_id"));
                    pt.println("All My Reimbursements: ");
                    printSelect.printRowWithOut(results,pt);
                    break;
                case "ViewReimById" :
                    reimId = request.getParameter("Rid");
                    reimList = reimService.viewOneReimbursementEmployee((Integer) UserSession.getUserSession()
                                    .getSession().getAttribute("user_id"),Integer.valueOf(reimId));
                    printSelect.printRow(reimList,pt);
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
        if (UserSession.getUserSession().getSession().getAttribute("role").equals(3)) {
            ReimbursementService reimService = new ReimbursementService();
            TypeHelper typeHelper = new TypeHelper();
            Integer integer;
            boolean updated;
            String reimtId;
            String type;
            String amount;
            String description;


            switch (method) {

                case "UpdateAll" :
                    reimtId = request.getParameter("Rid");
                    amount = request.getParameter("amount");
                    description = request.getParameter("description");
                    type = request.getParameter("type");

                    integer = ReimbursementType.getByName(type).ordinal()+1;
                    //integer=ReimbursementType.valueOf(type).ordinal()+1;
                    updated = reimService.updatePendingRowAll(Integer.valueOf(reimtId),Double.valueOf(amount),description,typeHelper.convertToEntityAttribute(integer));
                    if(updated){
                        pt.println("Updated amount, description,and type");
                    }
                    else{
                        pt.println("Not updated");
                    }
                    break;

                case "UpdateType":
                    reimtId = request.getParameter("Rid");
                    type = request.getParameter("type");
                    integer = ReimbursementType.getByName(type).ordinal()+1;
                    //integer=ReimbursementType.valueOf(type).ordinal()+1;
                    updated = reimService.updatePendingRowType(Integer.valueOf(reimtId),typeHelper.convertToEntityAttribute(integer));
                    if(updated){
                        pt.println("Updated type");
                    }
                    else{
                        pt.println("Not updated");
                    }
                    break;

                case "UpdateAmount":
                    reimtId = request.getParameter("Rid");
                    amount = request.getParameter("amount");

                    updated = reimService.updatePendingRowAmount(Integer.valueOf(reimtId),Double.valueOf(amount));
                    if(updated){
                        pt.println("Updated amount");
                    }
                    else{
                        pt.println("Not updated");
                    }
                    break;

                case "UpdateDescription":
                    reimtId = request.getParameter("Rid");
                    description = request.getParameter("amount");

                    updated = reimService.updatePendingRowDescription(Integer.valueOf(reimtId),description);
                    if(updated){
                        pt.println("Updated Description");
                    }
                    else{
                        pt.println("Not updated");
                    }
                    break;

                case "Add":
                     amount = request.getParameter("amount");
                    description = request.getParameter("description");
                    //String authorId = request.getParameter("authorId");
                    //  String status = request.getParameter("status");
                     type = request.getParameter("type");

                    Reimbursement reimbursement = new Reimbursement(
                            Double.valueOf(amount),//amount
                            description,//description
                           (Integer) UserSession.getUserSession().getSession().getAttribute("user_id"),//author ID
                            ReimbursementStatus.getByNumber(1),//reimbursement status
                            ReimbursementType.getByName(type));// reimbursement type
                    reimService.addReimbursement(reimbursement);
                    pt.println("Added Reimbursement");
                default:
                    response.setStatus(404);
            }
            }
        else{
            response.setStatus(401);
        }

        }

    }



