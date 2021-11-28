package com.finewine.core.exception;

public class OutOfStockException extends CustomException {

    public OutOfStockException() {

    }

    public OutOfStockException(String errorMessage) {
        super(errorMessage);
    }
}
