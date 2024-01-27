package com.tech.kj.config.security;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable {
    private int status;
    private String message;
    private Object result;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}