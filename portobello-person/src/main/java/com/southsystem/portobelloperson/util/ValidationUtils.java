package com.southsystem.portobelloperson.util;

import com.southsystem.portobelloperson.exception.PersonException;
import com.southsystem.portobelloperson.model.enumeration.PersonTypeEnum;

public class ValidationUtils {

    private ValidationUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static boolean validateDocument(String document, PersonTypeEnum personTypeEnum) throws PersonException {

        if (PersonTypeEnum.PF.equals(personTypeEnum) && document.length() > 11) {
            throw new PersonException("Invalid document length");
        }

        if (PersonTypeEnum.PJ.equals(personTypeEnum) && document.length() > 14) {
            throw new PersonException("Invalid document length");
        }

        return true;
    }

}
