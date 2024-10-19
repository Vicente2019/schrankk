package com.example.backend.exception;

public enum ErrorMessage {
    ITEMS_NOT_EXIST("Items with the following IDs do not exist: %s"),
    INVALID_ITEM_ID("Invalid Item ID: %s"),
    ITEM_ALREADY_IN_OUTFIT("Item with ID %s already in outfit"),
    ITEM_NOT_FOUND_IN_OUTFIT("Item with ID %s not found in outfit"),
    ITEM_NOT_FOUND("Item not found with id: %s"),

    ITEM_NAME_INVALID("Item Name: %s, is not valid"),
    ITEM_BRAND_INVALID("Item Brand: %s, is not valid"),
    ITEM_CATEGORY_INVALID("Item Category: %s, is not valid"),
    ITEM_PRICE_NEGATIVE("Price cannot be negative: %s"),
    ITEMS_NOT_ENOUGH_TAGS("Items must have at least 3 tags: %s"),

    OUTFIT_NAME_INVALID("Outfit Name: %s, is not valid"),
    OUTFIT_DESCRIPTION_INVALID("Outfit Description: %s, is not valid"),
    OUTFIT_MUST_HAVE_ITEMS("An outfit has one or more items"),
    OUTFIT_NOT_FOUND("Outfit not found with id: %s"),

    PLANNED_OUTFIT_NOT_FOUND_DATE("Planned Outfit not found with date: %s"),
    PLANNED_OUTFIT_NOT_FOUND_ID("Planned Outfit not found with id: %s"),
    PLANNED_DATE_CANNOT_BE_IN_THE_PAST("Planned Date cannot be in the past: %s"),
    OUTFIT_ALREADY_PLANNED_FOR_THIS_DATE("Outfit already planned for this date: %s");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(this.message, args);
    }
}
