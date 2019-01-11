package com.coreteka.exceptions;

public class DuplicateUserAttributeValueException extends InvalidUserRequestDataException {

    public DuplicateUserAttributeValueException(String message) {
        super(message);
    }
}
