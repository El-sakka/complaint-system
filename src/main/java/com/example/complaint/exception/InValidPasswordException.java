package com.example.complaint.exception;

public class InValidPasswordException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Invalid password";

    public InValidPasswordException() {
        super(DEFAULT_MESSAGE);
    }
    public InValidPasswordException(String message) {
        super(message);
    }

    public InValidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
