package com.backend.spades.model;

public enum UserRole {
    Admin,
    Manager,
    User;

    public boolean isAdmin() {
        return this == Admin;
    }

    public boolean isManager() {
        return this == Manager;
    }

    public boolean isUser() {
        return this == User;
    }
}
