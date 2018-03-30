package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.service.AuthService;

@Controller
public class AuthController {
    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = {"/login"})
    public String loginPage(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        map.addAttribute("error", (error != null));
        return "auth/login";
    }

    @GetMapping(value = {"/registration"})
    public String registerPage(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        map.addAttribute("error", (error != null));

        UserDTO user = new UserDTO();
        map.addAttribute("user_dto", user);

        return "auth/registration";
    }

    @PostMapping(value = {"/registration"})
    public String register(@ModelAttribute(value="user_dto") UserDTO user) {
        //TODO: confirm password!

        authService.register(user);

        return "redirect:/login";
    }
}