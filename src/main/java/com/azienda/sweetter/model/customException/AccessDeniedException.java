package com.azienda.sweetter.model.customException;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() { super(); }

    public AccessDeniedException(String message) { super(message); }
}
