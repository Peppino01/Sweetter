package com.azienda.sweetter.model.enums;

public enum Roles {
    ADMIN("User with maximum privileges"),
    USER("Standard user");

    private final String description;

    private Roles(String description) {
        this.description = description;
    }

    public String getDescription() {
            return description;
        }

    @Override
    public String toString() {
        return "Roles{" +
                "description='" + description + '\'' +
                '}';
    }
}