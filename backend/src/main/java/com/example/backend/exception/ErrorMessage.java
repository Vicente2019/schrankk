package com.example.backend.exception;

public enum ErrorMessage {
    ITEMS_NOT_EXIST("Items with the following IDs do not exist: %s");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(this.message, args);
    }
}
