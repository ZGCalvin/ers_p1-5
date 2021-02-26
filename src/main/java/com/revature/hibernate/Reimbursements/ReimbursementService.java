package com.revature.hibernate.Reimbursements;

import com.revature.exceptions.InvalidRequestException;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class ReimbursementService {

    private ReimbursementsRepository reimRepo = new ReimbursementsRepository();


    /**
     * Manager level service - adds a new reimbursement to the database
     * @param reimbursement the new reimbursement
     */
    public void addReimbursement(Reimbursement reimbursement){
        if (reimbursement==null){ throw new InvalidRequestException();}
        reimRepo.addReimbursement(reimbursement);
    }


    /**
     * Manager level service - queries all reimbursements in the database
     * @return a list containing all reimbursements
     */
    public List viewAllReimbursement(){
        return reimRepo.viewAllReimbursement();
    }

    /**
     * Employee level service - View all reimbursements associated with an employee
     * @param Id the id of the employee to be queried
     * @return a list of reimbursements
     */
    public List viewAllReimbursementEmployee(Integer Id){
        if (Id <= 0) {throw new InvalidRequestException();}
        return reimRepo.viewAllReimbursementEmployee(Id);
    }

    /**
     * Employee level service - view one reimbursement associated with an employee
     * @param id the id of the employee
     * @param reimId the id of the reimbursement
     * @return a list of one =)
     */
    public List<Reimbursement> viewOneReimbursementEmployee(Integer id, Integer reimId){
        if (id <= 0 || reimId <= 0) {throw new InvalidRequestException();}
        return reimRepo.viewOneReimbursementEmployee( id, reimId);
    }

    /**
     * Manager level service - view all reimbursements based on its status
     * @param status the status of reimbursement to be queried: Pending, Denied, Closed or Approved
     * @return a list of all reimbursements of a single reimbursement status
     */
    public List viewAllReimbursementByStatus(ReimbursementStatus status){
        if(isReimbursementStatusValid(status)){throw new InvalidRequestException();}
        return reimRepo.viewAllReimbursementbyStatus(status);
    }

    /**
     * manager level service - view all reimbursements based on its type
     * @param type the type of reimbursement to be queried: Food, Lodging, Travel, or Other
     * @return a list of all reimbursements of a single reimbursement type
     */
    public List viewAllReimbursementByType(ReimbursementType type){
        if(isReimbursementTypeValid(type)){throw new InvalidRequestException();}
        return reimRepo.viewAllReimbursementbyType(type);
    }

    //optional?
    public Optional<Reimbursement> getReimbursementById(Integer id){
        if (id <= 0 || id > 4) {throw new InvalidRequestException();}
        return reimRepo.getReimbursementById(id);
    }

    /**
     * Manager level service - Update the resolver id of a reimbursement
     * @param reimbursementId the id of the reimbursement to be updated
     * @param resolverId the id to enter into the resolver id field
     * @return
     */
    public boolean updateResolverByReimbursementId(Integer reimbursementId, Integer resolverId){
        if (resolverId <= 0 || reimbursementId <= 0) {throw new InvalidRequestException();}
        return reimRepo.updateResolverByReimbursementId(reimbursementId,resolverId);
    }

    /**
     * Manager level service -
     * @param reimbursementId  id of the reimbursement to be updated
     * @param date the new date
     * @param status the new status
     * @return  true if successful, false otherwise
     */
    public boolean updateStatusTime(Integer reimbursementId, Timestamp date, ReimbursementStatus status){
        if(isReimbursementStatusValid(status)){throw new InvalidRequestException();}
        return reimRepo.updateStatusTime(reimbursementId,date,status);
    }

    /**
     * Employee level service  - updates all aspects of a reimbursement based on the id
     * @param reimbursementId id of the reimbursement to be updated
     * @param amount the new amount
     * @param description the new description
     * @param type the new type
     * @return true if successful, false otherwise
     */
    public boolean updatePendingRowAll(Integer reimbursementId, Double amount, String description, ReimbursementType type){
        if(isReimbursementTypeValid(type)){throw new InvalidRequestException();}
        return reimRepo.updatePendingRowAll(reimbursementId, amount,description, type);
    }

    /**
     * Employee level service - updates the type of a reimbursement based on the id
     * @param rId id of the reimbursement to be updated
     * @param type the new type
     * @return true if successful, false otherwise
     */
    public boolean updatePendingRowType(Integer rId, ReimbursementType type){
        if(isReimbursementTypeValid(type)){throw new InvalidRequestException();}
        return reimRepo.updatePendingRowType(rId,type);
    }

    /**
     * Employee level service - updates the amount of a reimbursement based on the id
     * @param rId id of the reimbursement to be updated
     * @param amount the new amount
     * @return true if successful, false otherwise
     */
    public boolean updatePendingRowAmount(Integer rId, Double amount){
        if (rId <= 0) {throw new InvalidRequestException();}
        return reimRepo.updatePendingRowAmount(rId,amount);
    }

    /**
     * Employee level service - updates the description of a reimbursement of a 'Pending' status reimbursement
     * @param rId id of the reimbursement to be updated
     * @param d the new description
     * @return true if successful, false otherwise
     */
    public boolean updatePendingRowDescription(Integer rId, String d){
        if (rId <= 0 || d == null) {throw new InvalidRequestException();}
        return reimRepo.updatePendingRowDescription(rId,d);
    }


    /**
     * Validates that a given reimbursement status of a correct status type
     * @param r the status to be tested
     * @return true if valid, false otherwise
     */
    public Boolean isReimbursementStatusValid(ReimbursementStatus r) {
        if (r == null) return false;
        if (r != ReimbursementStatus.PENDING &&
                r != ReimbursementStatus.APPROVED &&
                r != ReimbursementStatus.DENIED &&
                r != ReimbursementStatus.CLOSED) return false;
        return true;
    }

    /**
     * Validates that a given reimbursement type of a correct type
     * @param t the type to be tested
     * @return true if valid, false otherwise
     */
    public Boolean isReimbursementTypeValid(ReimbursementType t) {
        if (t == null) return false;
        if (t != ReimbursementType.OTHER &&
                t != ReimbursementType.FOOD &&
                t != ReimbursementType.TRAVEL &&
                t != ReimbursementType.LODGING) return false;
        return true;
    }
}
