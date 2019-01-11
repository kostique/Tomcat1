package com.coreteka.exceptions;

public class ConstraintViolationFoundException extends InvalidUserRequestDataException {

    public ConstraintViolationFoundException(String message) {
        super(message);
    }
}
