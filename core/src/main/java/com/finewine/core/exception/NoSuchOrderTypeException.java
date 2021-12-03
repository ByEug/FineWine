package com.finewine.core.exception;

public class NoSuchOrderTypeException extends RuntimeException {

    private String type;

    public NoSuchOrderTypeException(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
