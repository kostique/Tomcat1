package com.coreteka.util;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationUtil {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    public static Validator getValidator() {
        if (validator == null) {
            validator = getValidatorFactory().usingContext().getValidator();
        }
        return validator;
    }


    private static ValidatorFactory getValidatorFactory() {
        if (validatorFactory == null) {
            validatorFactory =  Validation.buildDefaultValidatorFactory();
        }
        return validatorFactory;
    }
}
