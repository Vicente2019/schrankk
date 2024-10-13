package com.example.backend.exception;

public class ItemNotFoundException extends BusinessException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
