package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.service.AuthService;

@Controller
@ControllerAdvice
public class AdviseController {
    private AuthService authService;

    @ModelAttribute("authUser")
    public UserDTO getAuthUser() {
        return authService.getAuthUser();
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = {"/", "/index"})
    public String toIndexPage() {
        return "index";
    }

    @GetMapping(value = "/404")
    public String to404() {
        return "404";
    }

    @GetMapping(value = "/500")
    public String to500() {
        return "500";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex) {
        return "redirect:/404";
    }

//    @ExceptionHandler(Exception.class)
//    public String handle500(Exception ex) {
//        return "redirect:/500";
//    }
}
