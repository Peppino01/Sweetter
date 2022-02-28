package com.azienda.sweetter.model.customException;

public class RoleNotExistsException extends RuntimeException {
    RoleNotExistsException() { super(); }
    public RoleNotExistsException(String message) { super(message); }
}
