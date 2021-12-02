package com.finewine.core.exception;

public class EmptyDatabaseArgumentException extends CustomException {

    public EmptyDatabaseArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
