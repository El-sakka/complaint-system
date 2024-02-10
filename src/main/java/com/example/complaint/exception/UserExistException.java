package com.example.complaint.exception;

public class UserExistException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Username already exists";

    public UserExistException(){
        super(DEFAULT_MESSAGE);
    }
    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
