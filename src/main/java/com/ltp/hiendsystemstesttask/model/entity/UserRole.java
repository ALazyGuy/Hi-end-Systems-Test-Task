package com.ltp.hiendsystemstesttask.model.entity;

public enum UserRole {

    ROLE_USER(false), ROLE_ADMIN(true);

    private boolean isAdmin;

    UserRole(final boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
