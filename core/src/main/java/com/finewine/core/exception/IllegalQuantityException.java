package com.finewine.core.exception;

public class IllegalQuantityException extends CustomException {

    public IllegalQuantityException() {

    }

    public IllegalQuantityException(String errorMessage) {
        super(errorMessage);
    }
}
