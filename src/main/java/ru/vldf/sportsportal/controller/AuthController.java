package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vldf.sportsportal.service.UserService;

@Controller
public class AuthController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/login"})
    public String loginPage(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        map.addAttribute("error", (error != null));
        return "auth/login";
    }

    @GetMapping(value = {"/personalpage"})
    public String personalPage(ModelMap map) {
        map.addAttribute("username", userService.getAuthUsername());

        return "auth/personalpage";
    }

    @GetMapping(value = {"/registration"})
    public String registerPage(ModelMap map) {
        return "auth/registration";
    }
}