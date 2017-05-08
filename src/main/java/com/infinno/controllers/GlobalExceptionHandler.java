package com.infinno.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleGlobalExceptions(){
        return "exceptions/custom-error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(){
        return "/exceptions/404";

    }
//    ErrorController
}
