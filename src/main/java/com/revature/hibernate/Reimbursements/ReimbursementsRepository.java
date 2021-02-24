package com.revature.hibernate.Reimbursements;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReimbursementsRepository {
    private Session s;
    private String baseInsert = "INSERT INTO Reimbursement ";

    public ReimbursementsRepository(){
        s = HibernateUtil.getSessionFactory().openSession();
    }

    public void addReimbursement(Reimbursement reimbursement){

        s.beginTransaction();
        s.save(reimbursement);
        s.getTransaction().commit();
        s.close();

    }


}
