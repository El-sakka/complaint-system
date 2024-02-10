package com.example.complaint.exception;

public class UserException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "User not found";

    public UserException() {
        super(DEFAULT_MESSAGE);
    }
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
