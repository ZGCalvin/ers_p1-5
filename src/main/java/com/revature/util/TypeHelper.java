package com.revature.util;

import com.revature.models.ReimbursementType;

import javax.persistence.AttributeConverter;

/**
 * helper method so that the Type can be converted from an integer into an ENUM and vise versa
 */
public class TypeHelper implements AttributeConverter<ReimbursementType,Integer> {


    /**
     * converts from an ENUM to an Integer
     * @param reimbursementType the ENUM passed in that we need to use but have to  convert to Integer first
     * @return the Integer representation of the Type
     */
    @Override
    public Integer convertToDatabaseColumn(ReimbursementType reimbursementType) {
        if (reimbursementType == null) {
            return null;
        }
        switch (reimbursementType) {
            case LODGING:
                return 1;
            case TRAVEL:
                return 2;
            case FOOD:
                return 3;
            case OTHER:
                return 4;
            default:
                throw new IllegalArgumentException(reimbursementType + " invalid type");
        }
    }


    /**
     *  converts the Integer passed in back into the ENUM representation of it
     * @param integer the integer representation of the ENUM
     * @return the ENUM type of the integer passed in
     */
    @Override
    public ReimbursementType convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }
        switch (integer) {
            case 1:
                return ReimbursementType.LODGING;
            case 2:
                return ReimbursementType.TRAVEL;
            case 3:
                return ReimbursementType.FOOD;
            case 4:
                return ReimbursementType.OTHER;
            default:
                throw new IllegalArgumentException(integer + "invalid Integer");
        }
    }

}
