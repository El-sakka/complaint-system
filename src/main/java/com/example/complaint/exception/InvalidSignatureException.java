package com.example.complaint.exception;

public class InvalidSignatureException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Invalid signature";

    public InvalidSignatureException() {
        super(DEFAULT_MESSAGE);
    }
    public InvalidSignatureException(String message) {
        super(message);
    }

    public InvalidSignatureException(String message, Throwable cause) {
        super(message, cause);
    }
}
