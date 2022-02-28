package com.azienda.sweetter.model.customException;

public class PostNotExistsException extends RuntimeException {
    public PostNotExistsException() { super(); }

    public PostNotExistsException(String message) { super(message); }
}
