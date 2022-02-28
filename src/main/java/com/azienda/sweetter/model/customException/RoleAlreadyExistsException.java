package com.azienda.sweetter.model.customException;

public class RoleAlreadyExistsException extends RuntimeException {
    public RoleAlreadyExistsException() { super(); }

    public RoleAlreadyExistsException(String message) { super(message); }
}
