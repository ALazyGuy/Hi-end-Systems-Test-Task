package com.ltp.hiendsystemstesttask.exception;

public class AccountActionException extends Exception {
    private final String message;

    public AccountActionException(String message) {
        super(message);
        this.message = message;
    }

    public String getErrorMessage() {
        return message;
    }
}
