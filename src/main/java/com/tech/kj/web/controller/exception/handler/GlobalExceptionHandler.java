package com.tech.kj.web.controller.exception.handler;

import com.tech.kj.exception.RecordAlreadyExistException;
import com.tech.kj.exception.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({RecordAlreadyExistException.class, RecordNotFoundException.class})
    public ResponseEntity<Map<String,String>> handleRecordAlreadyExistException(RecordAlreadyExistException exception){
        Map<String,String> errorMap= Map.of("errorCode",exception.getErrorCode(), "errorMessage",exception.getMessage());
        return  ResponseEntity.badRequest().body(errorMap);
    }
}
