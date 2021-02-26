package com.revature.servlets;

import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name="userServlet",
        description= "User Login",
        urlPatterns = {"/userServlet"}
)

public class userServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        //       String status = request.getParameter("status");
        //       String type = request.getParameter("type");

//        String reimbursementid = request.getParameter("reimbursementid");
//        String status = request.getParameter("status");
//        String resolverid = request.getParameter("resolverid");

        //     String amount = request.getParameter("amount");
        //     String description = request.getParameter("description");
        String authorId = request.getParameter("authorId");
//        String status = request.getParameter("status");
        //     String type = request.getParameter("type");
//
//        //Insert into reimbursement
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
//        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
//        StatusHelper statusHelper = new StatusHelper();
////        List results = reimbursementsRepository.viewAllReimbursementbyStatus();
//        Integer integer = ReimbursementStatus.getByName(status).ordinal()+1;
//
//     //   Integer integer = Integer.valueOf(status);
//        List results = reimbursementsRepository.viewAllReimbursementbyStatus(statusHelper.convertToEntityAttribute(integer));
////        List results = reimbursementsRepository.viewAllReimbursementbyStatus(ReimbursementStatus.getByName(status));
//        pt.println("Int : " + integer);
//        pt.println("List of Reimbursements: " + results.toString());

        //view All reimbursements by Type
//        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
//        TypeHelper typeHelper = new TypeHelper();
////        List results = reimbursementsRepository.viewAllReimbursementbyStatus();
//        Integer integer = ReimbursementType.getByName(type).ordinal()+1;
//
//        //   Integer integer = Integer.valueOf(status);
//        List results = reimbursementsRepository.viewAllReimbursementbyType(typeHelper.convertToEntityAttribute(integer));
//
//        pt.println("Int : " + integer);
//        pt.println("List of Reimbursements: " + results.toString());


        //Update Resolver ID
//        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
//
//        boolean updated = reimbursementsRepository.updateResolverByReimbursementId(Integer.valueOf(reimbursementid),Integer.valueOf(resolverid));
//
//        if(updated){
//            pt.println("Updated");
//        }
//        else{
//            pt.println("Not updated");
//        }
//        pt.println("Testing update resolverId" );


        //update time and status
//        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
//        StatusHelper statusHelper = new StatusHelper();
//        Integer integer = ReimbursementStatus.getByName(status).ordinal()+1;
//
//
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        boolean updated = reimbursementsRepository.updateStatusTime(Integer.valueOf(reimbursementid), timestamp,statusHelper.convertToEntityAttribute(integer));
//
//        if(updated){
//            pt.println("Updated");
//        }
//        else{
//            pt.println("Not updated");
//        }
//        pt.println("Testing update time and status" );

        //view all columns for one employee
//        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
//        List results = reimbursementsRepository.viewAllReimbursementEmployee(Integer.valueOf(authorId));
//
//        pt.println("All reimbursements : "+ results.toString());
//        TypeHelper typeHelper = new TypeHelper();
//        Integer integer = ReimbursementType.getByName(type).ordinal()+1;
//        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
//        boolean updated = reimbursementsRepository.updatePendingRowAll(Integer.valueOf(reimbursementid),Double.valueOf(amount),description,typeHelper.convertToEntityAttribute(integer));
//
//        if(updated){
//            pt.println("Updated");
//        }
//        else{
//            pt.println("Nope");
//        }
//        pt.println("Updating Pending Row");

        //view one row from reimbursement
        //    ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
        //     List results = reimbursementsRepository.viewOneReimbursementEmployee(Integer.valueOf(authorId), Integer.valueOf(reimbursementid));

        //   pt.println("One result "+ results);

        //view all rows that employee sees(no details)
//        ReimbursementsRepository reimbursementsRepository = new ReimbursementsRepository();
//        List<Reimbursement> results = reimbursementsRepository.viewAllReimbursementEmployee(Integer.valueOf(authorId));
//        for(Reimbursement user: results){
//            pt.println("id :"+ user.getId());
//            pt.println("amount :" + user.getAmount());
//        }
        //pt.println(" list of reimbursement:"+results.toString());


        out.println("User Servlet");
    }

}
