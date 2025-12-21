package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles the "Capacity", "latitude", "exceeds", "past" errors
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleValidationErrors(IllegalArgumentException ex) {
        // Returns 400 Bad Request with your specific message
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handles "User not found", "Vehicle not found", etc.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        // Returns 404 Not Found
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}