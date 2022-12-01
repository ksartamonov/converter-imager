package com.kartamonov.imager.service.exceptions;

import java.util.UUID;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(UUID id) {
        super("Item with id = " + id + " not exists");
    }
}