package com.finewine.core.ajaxhandling;

public class ResponseHandlingInfo implements HandlingInfo {

    private String errorMessage;

    public ResponseHandlingInfo(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorsMessage() {
        return errorMessage;
    }

    public void setErrorsMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}