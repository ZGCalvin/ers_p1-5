package com.revature.hibernate.Reimbursements;

import com.revature.hibernate.users.UserRepository;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import com.revature.util.StatusHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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
        Query query = s.createQuery("From Reimbursement ");
        List results = query.getResultList();
        s.getTransaction().commit();
        s.close();
        return results;
    }


//    public List viewAllReimbursementEmployee(Integer id){
//        s.beginTransaction();
//        Query query = s.createQuery("From Reimbursement WHERE authorId =: id ").setParameter("id",id);
//        List results = query.getResultList();
//        s.getTransaction().commit();
//        s.close();
//        return results;
//    }

    public List viewAllReimbursementEmployee(Integer id){
        s.beginTransaction();
        Query query = s.createQuery("From Reimbursement r WHERE authorId = :id ")
                .setParameter("id",id);
        List results = query.getResultList();
        s.getTransaction().commit();
        s.close();
        return results;
    }


    public List<Reimbursement> viewOneReimbursementEmployee(Integer id, Integer reimId){
        s.beginTransaction();
        Query query = s.createQuery("From Reimbursement WHERE authorId =: id and id = :reimId")
                .setParameter("id",id)
                .setParameter("reimId",reimId);
        List<Reimbursement> results = query.getResultList();
        s.getTransaction().commit();
        s.close();
        return results;
    }

    public List viewAllReimbursementbyStatus(ReimbursementStatus Status){
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

    public List viewAllReimbursementbyType(ReimbursementType Type){

        s.beginTransaction();


        Query query = s.createQuery("From Reimbursement WHERE reimbursementType = :Type").setParameter("Type",Type);
        List results = query.getResultList();

        s.getTransaction().commit();
        s.close();

        return results;
    }

    public Optional<Reimbursement> getReimbursementById(Integer reimbursementId){
        Optional reimbursement = Optional.empty();


            s.beginTransaction();
            String hql = "FROM Reimbursement r where r.id = :id";
            reimbursement = s.createQuery(hql)
                    .setParameter("id", reimbursementId)
                    .stream()
                    .findFirst();

            s.close();
        return reimbursement;
    }
    public boolean updateResolverByReimbursementId(Integer reimbursementId, Integer resolverId ){
        boolean updated = false;
        Reimbursement reimbursement = null;
        try {
            s.beginTransaction();
            reimbursement =  s.get(Reimbursement.class, reimbursementId);
            reimbursement.setResolver(s.get(User.class, resolverId));
            s.save(reimbursement);

            s.getTransaction().commit();
            updated = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        s.close();
        return updated;
    }

   public boolean updateStatusTime(Integer reimbursementId, Timestamp date, ReimbursementStatus status){
        boolean updated = false;

        Reimbursement reimbursement;
        try {
            s.beginTransaction();
            reimbursement = s.get(Reimbursement.class, reimbursementId);
            reimbursement.setResolved(date);
            reimbursement.setReimbursementStatus(status);
            s.save(reimbursement);
            s.getTransaction().commit();
            updated = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        s.close();
        return updated;
    }

    public boolean updatePendingRowAll(Integer reimbursementId, Double amount, String description, ReimbursementType type){

        boolean updated = false;
        ReimbursementStatus status = null;
        Reimbursement reimbursement;
        try {

            //check status
            Query query = s.createQuery("From Reimbursement WHERE id =:reimbursementId AND reimbursementStatus = 1")
                    .setParameter("reimbursementId", reimbursementId);
            List<Reimbursement> results = query.getResultList();
            for(Reimbursement u : results){
               status = u.getReimbursementStatus();
            }

            if(status.equals(ReimbursementStatus.PENDING)) {
                s.beginTransaction();
                reimbursement = s.get(Reimbursement.class, reimbursementId);
                reimbursement.setAmount(amount);
                reimbursement.setDescription(description);
                reimbursement.setReimbursementType(type);
                s.save(reimbursement);
                s.getTransaction().commit();
                updated = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        s.close();
        return updated;

    }


    public boolean updatePendingRowType(Integer reimbursementId, ReimbursementType type){

        boolean updated = false;
        ReimbursementStatus status = null;
        Reimbursement reimbursement;
        try {

            //check status
            Query query = s.createQuery("From Reimbursement WHERE id =:reimbursementId AND reimbursementStatus = 1")
                    .setParameter("reimbursementId", reimbursementId);
            List<Reimbursement> results = query.getResultList();
            for(Reimbursement u : results){
                status = u.getReimbursementStatus();
            }

            if(status.equals(ReimbursementStatus.PENDING)) {
                s.beginTransaction();
                reimbursement = s.get(Reimbursement.class, reimbursementId);
                reimbursement.setReimbursementType(type);
                s.save(reimbursement);
                s.getTransaction().commit();
                updated = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        s.close();
        return updated;

    }


    public boolean updatePendingRowAmount(Integer reimbursementId, Double amount){

        boolean updated = false;
        ReimbursementStatus status = null;
        Reimbursement reimbursement;
        try {

            //check status
            Query query = s.createQuery("From Reimbursement WHERE id =:reimbursementId AND reimbursementStatus = 1")
                    .setParameter("reimbursementId", reimbursementId);
            List<Reimbursement> results = query.getResultList();
            for(Reimbursement u : results){
                status = u.getReimbursementStatus();
            }

            if(status.equals(ReimbursementStatus.PENDING)) {
                s.beginTransaction();
                reimbursement = s.get(Reimbursement.class, reimbursementId);
                reimbursement.setAmount(amount);
                s.save(reimbursement);
                s.getTransaction().commit();
                updated = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        s.close();
        return updated;

    }

    public boolean updatePendingRowDescription(Integer reimbursementId, String description){

        boolean updated = false;
        ReimbursementStatus status = null;
        Reimbursement reimbursement;
        try {

            //check status
            Query query = s.createQuery("From Reimbursement WHERE id =:reimbursementId AND reimbursementStatus = 1")
                    .setParameter("reimbursementId", reimbursementId);
            List<Reimbursement> results = query.getResultList();
            for(Reimbursement u : results){
                status = u.getReimbursementStatus();
            }

            if(status.equals(ReimbursementStatus.PENDING)) {
                s.beginTransaction();
                reimbursement = s.get(Reimbursement.class, reimbursementId);
                reimbursement.setDescription(description);
                s.save(reimbursement);
                s.getTransaction().commit();
                updated = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        s.close();
        return updated;

    }

}
