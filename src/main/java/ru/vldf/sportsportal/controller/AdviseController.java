package ru.vldf.sportsportal.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class AdviseController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(NoHandlerFoundException ex) {
        return "404";
    }
}
