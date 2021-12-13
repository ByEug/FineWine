package com.finewine.core.exception;

public class IllegalRoleException extends RuntimeException {

    private String exc;

    public IllegalRoleException(String exc) {
        this.exc = exc;
    }

    public String getExc() {
        return exc;
    }

    public void setExc(String exc) {
        this.exc = exc;
    }
}
