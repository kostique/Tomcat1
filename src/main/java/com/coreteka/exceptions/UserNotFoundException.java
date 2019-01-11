package com.coreteka.exceptions;

public class UserNotFoundException extends InvalidUserRequestDataException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
