package com.revature.hibernate.Reimbursements;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import com.revature.util.StatusHelper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReimbursementsRepository {
    private Session s;
    private StatusHelper statusHelper;
    private String baseInsert = "INSERT INTO Reimbursement ";

    public ReimbursementsRepository(){
        s = HibernateUtil.getSessionFactory().openSession();
        statusHelper = new StatusHelper();
    }

    public void addReimbursement(Reimbursement reimbursement){

        s.beginTransaction();
        s.save(reimbursement);
        s.getTransaction().commit();
        s.close();

    }

    public List viewAllReimbursement(){
        s.beginTransaction();
        String baseQuery = "FROM User ";
        String whereUserNPass = " WHERE username = :user AND password = :pass";
        Query query = s.createQuery("From Reimbursement ");
        List results = query.getResultList();
        s.getTransaction().commit();
        s.close();
        return results;
    }

    public List viewAllReimbursementbyStatus(ReimbursementStatus Status){
//        s.beginTransaction();
//        String baseQuery = "FROM User ";
//        String whereUserNPass = " WHERE username = :user AND password = :pass";
//        String where = " Where RS.id = R.reimbursement_status_id ";
//        Query query = s.createQuery("Select RS.role_name ,R.id, R.amount, R.description From Reimbursement R, ReimbursementStatus RS " +
//                    where);
//        List results = query.getResultList();
//        s.getTransaction().commit();
//        s.close();
        int statusnum = -1;
        s.beginTransaction();
        String baseQuery = "FROM User ";
        String whereUserNPass = " WHERE username = :user AND password = :pass";

//        Query query = s.createQuery("From Reimbursement R WHERE R.reimbursementStatus = :Status").setParameter("Status",Status);
        Query query = s.createQuery("From Reimbursement WHERE reimbursementStatus = :Status").setParameter("Status",Status);
        List results = query.getResultList();
//        for(Reimbursement u : results){
//           //u.setReimbursementStatus(statusHelper.convertToEntityAttribute(u.getReimbursementStatus().ordinal()+1));
//           statusnum = u.getReimbursementStatus().ordinal();
//        }
        s.getTransaction().commit();
        s.close();

        return results;
    }

    public boolean updateReimbursement( ){
        return false;
    }


}
