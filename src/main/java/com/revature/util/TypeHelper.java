package com.revature.util;

import com.revature.models.ReimbursementType;

import javax.persistence.AttributeConverter;

public class TypeHelper implements AttributeConverter<ReimbursementType,Integer> {


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
