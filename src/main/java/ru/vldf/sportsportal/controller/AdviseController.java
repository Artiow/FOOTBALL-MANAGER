package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.vldf.sportsportal.service.AuthService;

@Controller
@ControllerAdvice
public class AdviseController {
    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = {"/", "/index"})
    public String toIndexPage(ModelMap map) {
        map.addAttribute("username", authService.getAuthUsername());
        return "index";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(NoHandlerFoundException ex) {
        return "404";
    }
}
