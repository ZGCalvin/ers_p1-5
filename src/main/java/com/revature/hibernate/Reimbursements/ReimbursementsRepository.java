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

/**
 * This class is responsible for getting data from the database using hibernate
 */
public class ReimbursementsRepository {

    private Session s;
    private StatusHelper statusHelper;
    private String baseInsert = "INSERT INTO Reimbursement ";

    /**
     * Constructor for initializating hibernate session and statusHelper
     */
    public ReimbursementsRepository(){
        s = HibernateUtil.getSessionFactory().openSession();
        statusHelper = new StatusHelper();
    }

    /**
     * Adds a reimbursement row into the database
     * @param reimbursement
     */
    public void addReimbursement(Reimbursement reimbursement){

        try {
            s.beginTransaction();
            s.save(reimbursement);
            s.getTransaction().commit();
            s.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * returns a list of all Reimbursement in the Reimbursement table
     * @return list of all reimbursement
     */
    public List viewAllReimbursement(){
        List results = null;
        try {
            s.beginTransaction();
            Query query = s.createQuery("From Reimbursement ");
             results = query.getResultList();
            s.getTransaction().commit();
            s.close();
            return results;
        }
        catch (Exception e){
            e.printStackTrace();
        }
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

    /**
     * returns a list of all Reimbursement for a specific employee
     * @param id
     * @return a list of all Reimbursement for a specific employee
     */
    public List viewAllReimbursementEmployee(Integer id){
        List results = null;
        try {
            s.beginTransaction();
            Query query = s.createQuery("From Reimbursement r WHERE authorId = :id ")
                    .setParameter("id", id);
            results = query.getResultList();
            s.getTransaction().commit();
            s.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }


    /**
     * return one row containing the information about one Reimbursement
     * @param id
     * @param reimId
     * @return one row containing the information about one Reimbursement
     */
    public List<Reimbursement> viewOneReimbursementEmployee(Integer id, Integer reimId){
        List<Reimbursement> results = null;
        try {

            s.beginTransaction();
            Query query = s.createQuery("From Reimbursement WHERE authorId =: id and id = :reimId")
                    .setParameter("id", id)
                    .setParameter("reimId", reimId);
            results = query.getResultList();
            s.getTransaction().commit();
            s.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * returns all reimbursement by status code
     * @param Status
     * @return returns all reimbursement by status code
     */
    public List viewAllReimbursementbyStatus(ReimbursementStatus Status){
        List results =null;
        try {
            int statusnum = -1;
            s.beginTransaction();
            String baseQuery = "FROM User ";
            String whereUserNPass = " WHERE username = :user AND password = :pass";

//        Query query = s.createQuery("From Reimbursement R WHERE R.reimbursementStatus = :Status").setParameter("Status",Status);
            Query query = s.createQuery("From Reimbursement WHERE reimbursementStatus = :Status").setParameter("Status", Status);
            results = query.getResultList();
//        for(Reimbursement u : results){
//           //u.setReimbursementStatus(statusHelper.convertToEntityAttribute(u.getReimbursementStatus().ordinal()+1));
//           statusnum = u.getReimbursementStatus().ordinal();
//        }
            s.getTransaction().commit();
            s.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * returns all Reimbursement by the type of reimbursement
     * @param Type
     * @return returns all Reimbursement by the type of reimbursement
     */
    public List viewAllReimbursementbyType(ReimbursementType Type){
        List results = null;
        try {
            s.beginTransaction();


            Query query = s.createQuery("From Reimbursement WHERE reimbursementType = :Type").setParameter("Type", Type);
            results = query.getResultList();

            s.getTransaction().commit();
            s.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * this method is able to get the reimbursement by the id
     * @param reimbursementId
     * @return get reimbursement by the id
     */
    public Optional<Reimbursement> getReimbursementById(Integer reimbursementId){
        Optional reimbursement = Optional.empty();
        try {
            s.beginTransaction();
            String hql = "FROM Reimbursement r where r.id = :id";
            reimbursement = s.createQuery(hql)
                    .setParameter("id", reimbursementId)
                    .stream()
                    .findFirst();

            s.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    /**
     * this method updates Resolver ID by using Reimbursement Id
     * @param reimbursementId
     * @param resolverId
     * @return true if updates Resolver ID by using Reimbursement Id else false
     */
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

    /**
     * This method updates the status and time by reimbursement id
     * @param reimbursementId
     * @param date
     * @param status
     * @return true if its updated else false
     */
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

    /**
     * This method updates all pending rows
     * @param reimbursementId
     * @param amount
     * @param description
     * @param type
     * @return true if updated was successful else false
     */
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


    /**
     * Updated the type of reimbursement using reimbursement id
     * @param reimbursementId
     * @param type
     * @return true if its updated else false
     */
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


    /**
     * this method updates amount using reimbursement id
     * @param reimbursementId
     * @param amount
     * @return true if updated else false
     */
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

    /**
     * this method updates the description using reimbursement ID
     * @param reimbursementId
     * @param description
     * @return true if it's updated else false
     */
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
