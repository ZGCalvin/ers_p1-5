package com.revature.util;

import com.revature.models.ReimbursementStatus;

import javax.persistence.AttributeConverter;

public class StatusHelper implements AttributeConverter<ReimbursementStatus,Integer> {


    @Override
    public Integer convertToDatabaseColumn(ReimbursementStatus reimbursementStatus) {
        if( reimbursementStatus == null){
            return null;
        }
        switch (reimbursementStatus){

            case PENDING:
                return 1;
            case APPROVED:
                return 2;
            case DENIED:
                return 3;
            case CLOSED:
                return 4;
            default:
                throw new IllegalArgumentException(reimbursementStatus + " invalid!");
        }

    }

    @Override
    public ReimbursementStatus convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }
        switch (integer) {
            case 1:
                return ReimbursementStatus.PENDING;
            case 2:
                return ReimbursementStatus.APPROVED;
            case 3:
                return ReimbursementStatus.DENIED;
            case 4:
                return ReimbursementStatus.CLOSED;
            default:
                throw new IllegalArgumentException(integer + " not supported");
        }
    }
}
