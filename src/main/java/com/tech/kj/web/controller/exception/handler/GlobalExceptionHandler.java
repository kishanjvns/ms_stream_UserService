package com.tech.kj.web.controller.exception.handler;

import com.tech.kj.exception.ApplicationConstant;
import com.tech.kj.exception.RecordAlreadyExistException;
import com.tech.kj.exception.RecordNotFoundException;
import com.tech.kj.exception.ValidationBaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ValidationBaseException.class})
    public ResponseEntity<Map<String,String>> handleRecordAlreadyExistException(ValidationBaseException exception){
        Map<String,String> errorMap= Map.of("errorCode",exception.getErrorCode(), "errorMessage",exception.getMessage());
        return  ResponseEntity.badRequest().body(errorMap);
    }
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResponseEntity<Map<String,String>> handleRecordAlreadyExistException(InternalAuthenticationServiceException exception){
        Map<String,String> errorMap= Map.of("errorCode", ApplicationConstant.ERR_NOT_FOUND, "errorMessage",ApplicationConstant.ERR_NOT_FOUND_MSG);
        return  ResponseEntity.badRequest().body(errorMap);
    }
}
