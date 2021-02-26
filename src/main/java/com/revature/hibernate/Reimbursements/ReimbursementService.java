package com.revature.hibernate.Reimbursements;

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
        reimRepo.addReimbursement(reimbursement);
    }


    //manager
    public List viewAllReimbursement(){
        return reimRepo.viewAllReimbursement();
    }

    //Employee
    public List viewAllReimbursementEmployee(Integer Id){
        return reimRepo.viewAllReimbursementEmployee(Id);
    }

    //Employee
    public List<Reimbursement> viewOneReimbursementEmployee(Integer id, Integer reimId){

        return reimRepo.viewOneReimbursementEmployee( id, reimId);
    }

    //manager
    public List viewAllReimbursementByStatus(ReimbursementStatus status){
        return reimRepo.viewAllReimbursementbyStatus(status);
    }

    //manager
    public List viewAllReimbursementByType(ReimbursementType type){
        return reimRepo.viewAllReimbursementbyType(type);
    }

    //optional?
    public Optional<Reimbursement> getReimbursementById(Integer id){
        return reimRepo.getReimbursementById(id);
    }

    //manager
    public boolean updateResolverByReimbursementId(Integer reimbursementId, Integer resolverId){
        return reimRepo.updateResolverByReimbursementId(reimbursementId,resolverId);
    }

    //manager
    public boolean updateStatusTime(Integer reimbursementId, Timestamp date, ReimbursementStatus status){
        return reimRepo.updateStatusTime(reimbursementId,date,status);
    }

    //Employee
    public boolean updatePendingRowAll(Integer reimbursementId, Double amount, String description, ReimbursementType type){
        return reimRepo.updatePendingRowAll(reimbursementId, amount,description, type);
    }

    //Employee
    public boolean updatePendingRowType(Integer rId, ReimbursementType type){
        return reimRepo.updatePendingRowType(rId,type);
    }

    //Employee
    public boolean updatePendingRowAmount(Integer rId, Double amount){
        return reimRepo.updatePendingRowAmount(rId,amount);
    }

    //Employee
    public boolean updatePendingRowDescription(Integer rId, String d){
        return reimRepo.updatePendingRowDescription(rId,d);
    }

}
