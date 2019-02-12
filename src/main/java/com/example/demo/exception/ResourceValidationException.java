package com.example.demo.exception;

/**
 * Custom exception for resource validation
 */
public class ResourceValidationException extends Exception {

    private String message;

    public ResourceValidationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
