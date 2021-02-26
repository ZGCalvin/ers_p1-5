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




    //manager
    public void addReimbursement(Reimbursement reimbursement){
        if (reimbursement==null){ throw new InvalidRequestException();}
        reimRepo.addReimbursement(reimbursement);
    }

    //manager
    public List viewAllReimbursement(){
        return reimRepo.viewAllReimbursement();
    }

    //Employee
    public List viewAllReimbursementEmployee(Integer Id){
        if (Id <= 0) {throw new InvalidRequestException();}
        return reimRepo.viewAllReimbursementEmployee(Id);
    }

    //Employee
    public List<Reimbursement> viewOneReimbursementEmployee(Integer id, Integer reimId){
        if (id <= 0 || reimId <= 0) {throw new InvalidRequestException();}
        return reimRepo.viewOneReimbursementEmployee( id, reimId);
    }

    //manager
    public List viewAllReimbursementByStatus(ReimbursementStatus status){
        if(isReimbursementStatusValid(status)){throw new InvalidRequestException();}
        return reimRepo.viewAllReimbursementbyStatus(status);
    }

    //manager
    public List viewAllReimbursementByType(ReimbursementType type){
        if(isReimbursementTypeValid(type)){throw new InvalidRequestException();}
        return reimRepo.viewAllReimbursementbyType(type);
    }

    //optional?
    public Optional<Reimbursement> getReimbursementById(Integer id){
        if (id <= 0 || id > 4) {throw new InvalidRequestException();}
        return reimRepo.getReimbursementById(id);
    }

    //manager
    public boolean updateResolverByReimbursementId(Integer reimbursementId, Integer resolverId){
        if (resolverId <= 0 || reimbursementId <= 0) {throw new InvalidRequestException();}
        return reimRepo.updateResolverByReimbursementId(reimbursementId,resolverId);
    }

    //manager
    public boolean updateStatusTime(Integer reimbursementId, Timestamp date, ReimbursementStatus status){
        if(isReimbursementStatusValid(status)){throw new InvalidRequestException();}
        return reimRepo.updateStatusTime(reimbursementId,date,status);
    }

    //Employee
    public boolean updatePendingRowAll(Integer reimbursementId, Double amount, String description, ReimbursementType type){
        if(isReimbursementTypeValid(type)){throw new InvalidRequestException();}
        return reimRepo.updatePendingRowAll(reimbursementId, amount,description, type);
    }

    //Employee
    public boolean updatePendingRowType(Integer rId, ReimbursementType type){
        if(isReimbursementTypeValid(type)){throw new InvalidRequestException();}
        return reimRepo.updatePendingRowType(rId,type);
    }

    //Employee
    public boolean updatePendingRowAmount(Integer rId, Double amount){
        if (rId <= 0) {throw new InvalidRequestException();}
        return reimRepo.updatePendingRowAmount(rId,amount);
    }

    //Employee
    public boolean updatePendingRowDescription(Integer rId, String d){
        if (rId <= 0 || d == null) {throw new InvalidRequestException();}
        return reimRepo.updatePendingRowDescription(rId,d);
    }



    public Boolean isReimbursementStatusValid(ReimbursementStatus r) {
        if (r == null) return false;
        if (r != ReimbursementStatus.PENDING &&
                r != ReimbursementStatus.APPROVED &&
                r != ReimbursementStatus.DENIED &&
                r != ReimbursementStatus.CLOSED) return false;
        return true;
    }

    public Boolean isReimbursementTypeValid(ReimbursementType t) {
        if (t == null) return false;
        if (t != ReimbursementType.OTHER &&
                t != ReimbursementType.FOOD &&
                t != ReimbursementType.TRAVEL &&
                t != ReimbursementType.LODGING) return false;
        return true;
    }
}
