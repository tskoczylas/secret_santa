package com.tomsproject.secret_santa.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {IllegalStateException.class})
    protected ResponseEntity<?> handleStateException(RuntimeException ex, WebRequest request){
        ExceptionMessageDto response = ExceptionMessageDto.builder().errorName(ex.toString()).methodMessage(ex.getMessage()).errorMessage(ex.getLocalizedMessage()).build();
        return handleExceptionInternal(ex,response,new HttpHeaders(), HttpStatus.CONFLICT,request);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<?> handleNullPoniterExeption(RuntimeException ex, WebRequest request){
        ExceptionMessageDto response = ExceptionMessageDto.builder().errorName(ex.toString()).methodMessage(ex.getMessage()).errorMessage(ex.getLocalizedMessage()).build();
        return handleExceptionInternal(ex,response,new HttpHeaders(), HttpStatus.CONFLICT,request);
    }

    @ExceptionHandler(value = {SecretServiceException.class})
    protected ResponseEntity<?> handleDBConnectionException(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex,ex.getMessage(),new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,request);
    }


}
