package com.example.backend.exception;

public enum ErrorMessage {
    ITEMS_NOT_EXIST("Items with the following IDs do not exist: %s"),
    INVALID_ITEM_ID("Invalid Item ID: %s"),
    ITEM_ALREADY_IN_OUTFIT("Item with ID %s already in outfit"),
    ITEM_NOT_FOUND_IN_OUTFIT("Item with ID %s not found in outfit"),
    ITEM_NOT_FOUND("Item not found with id: %s"),

    OUTFIT_NAME_INVALID("Outfit Name: %s, is not valid"),
    OUTFIT_DESCRIPTION_INVALID("Outfit Description: %s, is not valid"),
    OUTFIT_MUST_HAVE_ITEMS("An outfit has one or more items"),
    OUTFIT_NOT_FOUND("Outfit not found with id: %s");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(this.message, args);
    }
}
