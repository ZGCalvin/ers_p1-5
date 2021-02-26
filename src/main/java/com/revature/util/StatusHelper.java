package com.revature.util;

import com.revature.models.ReimbursementStatus;

import javax.persistence.AttributeConverter;

/**
 * This class help by converting an ENUM,  it can convert to an Integer, and also take in an integer
 * to convert it back into the ENUM representation
 */
public class StatusHelper implements AttributeConverter<ReimbursementStatus,Integer> {

    /**
     * convert the status of the ReimbursmentStatus passed in to an Integer
     * @param reimbursementStatus the column of the Reimbursment table, it is an enum so we need to convert to Integer
     * @return returns Integer object
     */
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


    /**
     * converts the integer passed in into the ENUM representation
     * @param integer the integer representation of the status
     * @return returns the ENUM
     */
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
