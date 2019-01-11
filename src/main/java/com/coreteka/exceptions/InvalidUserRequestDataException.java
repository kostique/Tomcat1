package com.coreteka.exceptions;

public class InvalidUserRequestDataException extends RuntimeException{

    InvalidUserRequestDataException(String message) {
        super(message);
    }
}
