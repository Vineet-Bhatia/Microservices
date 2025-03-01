package com.Icwd.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)  // Returns 404 status code automatically
public class ResourceNotFoundException extends RuntimeException {
    
    // Default message constructor
    public ResourceNotFoundException() {
        super("Resource not found on server!!");
    }
    
    // Custom message constructor
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

