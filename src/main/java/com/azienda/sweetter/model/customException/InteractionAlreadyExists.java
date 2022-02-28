package com.azienda.sweetter.model.customException;

public class InteractionAlreadyExists extends RuntimeException {
    public InteractionAlreadyExists() { super(); }

    public InteractionAlreadyExists(String message) { super(message); }
}
