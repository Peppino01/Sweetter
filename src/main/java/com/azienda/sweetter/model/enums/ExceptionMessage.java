package com.azienda.sweetter.model.enums;

public enum ExceptionMessage {
    // User
    USER_NOT_EXISTS(1, "The user with these credentials does not exist."),
    USER_WRONG_CREDENTIALS(2, "Wrong credentials."),
    USERNAME_ALREADY_EXISTS(3, "A user with this username does already exist."),
    EMAIL_ALREADY_EXISTS(4, "A user with this email does already exist."),
    POST_ACCESS_DENIED(5, "You are not the post owner."),

    // Post
    POST_NOT_EXISTS(5, "This post does not exist."),
    LIKE_ALREADY_EXISTS(6, "You already put like to this post"),
    UNLIKE_ALREADY_EXISTS(7, "You already put unlike to this post"),
    POST_OWNER_LIKE(8, "A post owner cannot add like or unlike to his posts."),

    // Role
    ROLE_NOT_EXISTS(9, "This role does not exist in the system."),
    ROLE_ALREADY_EXISTS(10, "This role does already exist in the system."),

    // Generic
    INVALID_FIELDS(11, "Invalid fields! Check if you put all fields correctly."),
    GENEREIC_ERROR(12, "Oops, something went wrong! Please try again.");

    private final int code;
    private final String message;

    private ExceptionMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ExceptionMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
