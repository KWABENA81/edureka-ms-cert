package com.edu.issuerms.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IssuerNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(IssuerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String issuerNotFoundHandler(IssuerNotFoundException ex) {
        return ex.getMessage();
    }
}
