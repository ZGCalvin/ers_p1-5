package com.revature.hibernate.Reimbursements;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReimbursementsRepository {
    private Session s;
    private String baseInsert = "INSERT INTO Reimbursement ";


    public void addReimbursement(Reimbursement reimbursement){

        s.beginTransaction();
        s.save(reimbursement);
        s.getTransaction().commit();
        s.close();

    }


}
