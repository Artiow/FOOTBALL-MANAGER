package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.vldf.sportsportal.service.UserService;

@Controller
@ControllerAdvice
public class AdviseController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", "/index"})
    public String indexPage(ModelMap map) {
        map.addAttribute("username", userService.getAuthUsername());

        return "index";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(NoHandlerFoundException ex) {
        return "404";
    }
}
