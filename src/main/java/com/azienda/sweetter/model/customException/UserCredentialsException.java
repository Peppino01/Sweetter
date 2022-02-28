package com.azienda.sweetter.model.customException;

public class UserCredentialsException extends RuntimeException {
    public UserCredentialsException() { super(); }

    public UserCredentialsException(String message) {
        super(message);
    }
}
