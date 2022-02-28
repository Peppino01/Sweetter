package com.azienda.sweetter.model.customException;

public class PostOwnerLikeException extends RuntimeException {
    public PostOwnerLikeException() {}

    public PostOwnerLikeException(String message) { super(message); }
}
