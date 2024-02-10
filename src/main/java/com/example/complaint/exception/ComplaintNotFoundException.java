package com.example.complaint.exception;

public class ComplaintNotFoundException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Complaint not found";

    public ComplaintNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public ComplaintNotFoundException(String message) {
        super(message);
    }

    public ComplaintNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

