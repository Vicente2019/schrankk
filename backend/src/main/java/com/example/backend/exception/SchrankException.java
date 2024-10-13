package com.example.backend.exception;

public class SchrankException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public SchrankException(ErrorMessage errorMessage, Object... args) {
        super(errorMessage.getMessage(args));
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
