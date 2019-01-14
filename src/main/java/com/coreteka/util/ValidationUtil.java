package com.coreteka.util;

import com.coreteka.exceptions.ConstraintViolationFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

    public class ValidationUtil {

        private static ValidatorFactory validatorFactory;
        private static Validator validator;
        private static StringBuffer messageBuffer = new StringBuffer();
        private static final String HIDDEN_PASSWORD = "****";


    private static Validator getValidator() {
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

    public static <T> void  validateEntityConstraints(T entity) {
        Set<ConstraintViolation<T>> constraintViolations = ValidationUtil.getValidator().validate(entity);
        if(constraintViolations.size() > 0) {
            String header = "Constraint violations found in: \n";
            messageBuffer.setLength(0);
            messageBuffer.append(header);
            constraintViolations
                    .stream()
                    .map(ValidationUtil::composeMessage)
                    .forEach(message -> messageBuffer.append(message));
            throw new ConstraintViolationFoundException(messageBuffer.toString());
        }
    }

    private static <T> StringBuffer composeMessage(ConstraintViolation<T> userConstraintViolation){
        String value;
        String propertyPath = userConstraintViolation.getPropertyPath().toString();
        String constraintMessage = userConstraintViolation.getMessage();
        if (propertyPath.equals("password") & !constraintMessage.equals("may not be null")){
            value = HIDDEN_PASSWORD;
        } else {
            value = (String)userConstraintViolation.getInvalidValue();
        }
        return (new StringBuffer()
                .append(propertyPath)
                .append(":  ")
                .append(value)
                .append("  (")
                .append(constraintMessage)
                .append(")\n"));
    }
}