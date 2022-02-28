package com.azienda.sweetter.model.customException;

public class InvalidFieldsException extends RuntimeException {
    public InvalidFieldsException() { super(); }

    public InvalidFieldsException(String message) { super(message); }
}
