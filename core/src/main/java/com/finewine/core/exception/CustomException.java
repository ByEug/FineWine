package com.finewine.core.exception;

public class CustomException extends Exception {

    public String errorMessage;

    public CustomException() {

    }

    public CustomException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
