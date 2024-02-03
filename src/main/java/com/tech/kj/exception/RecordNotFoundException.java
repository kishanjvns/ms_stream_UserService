package com.tech.kj.exception;


public class RecordNotFoundException extends ApplicationBaseException {
    private String errorCode;//ERROR-USR-001

    public RecordNotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }
}
