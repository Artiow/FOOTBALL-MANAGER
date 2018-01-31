package ru.vldf.sportsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping(value = {"/login"})
    public String loginPage(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        map.addAttribute("error", (error != null));
        return "login";
    }

    @GetMapping(value = {"/registration"})
    public String registerPage(ModelMap map) {
        return "registration";
    }
}