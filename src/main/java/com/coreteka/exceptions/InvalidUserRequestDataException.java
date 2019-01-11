package com.coreteka.exceptions;

public class InvalidUserRequestDataException extends RuntimeException{

   public InvalidUserRequestDataException(String message) {
        super(message);
    }
}
