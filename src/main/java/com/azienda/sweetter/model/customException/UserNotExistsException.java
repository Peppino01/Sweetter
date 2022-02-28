package com.azienda.sweetter.model.customException;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException() { super(); }

    public UserNotExistsException(String message) {
        super(message);
    }
}
