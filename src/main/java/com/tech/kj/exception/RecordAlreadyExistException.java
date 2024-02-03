package com.tech.kj.exception;


public class RecordAlreadyExistException extends ApplicationBaseException {
    private String errorCode;//ERROR-USR-001

    public RecordAlreadyExistException(String errorCode, String message) {
        super(errorCode, message);
    }
}
