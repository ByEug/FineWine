package com.finewine.core.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {

    private String email;

    public EmailAlreadyRegisteredException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
