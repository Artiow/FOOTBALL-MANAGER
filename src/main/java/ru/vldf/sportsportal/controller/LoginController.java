package ru.vldf.sportsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"/login"})
    public String loginPage(ModelMap map) {
        return "login";
    }

    @GetMapping(value = {"/register"})
    public String registerPage(ModelMap map) {
        return "register";
    }
}