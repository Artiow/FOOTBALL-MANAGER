package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.service.AuthService;

@Controller
public class AuthController {
    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping(value = {"/login"})
    public String toLoginPage(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        map.addAttribute("error", (error != null));
        return "auth/login";
    }

    @GetMapping(value = {"/registration"})
    public String toRegisterPage(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        map
                .addAttribute("error", (error != null))
                .addAttribute("userDTO", new UserDTO());

        return "auth/registration";
    }

    @PostMapping(value = {"/registration"})
    public String register(@ModelAttribute(value="userDTO") UserDTO user) {
        if (!authService.check(user)) return "redirect:/registration?error=true";
        authService.register(user); //TODO: confirm password check!
        return "redirect:/login";
    }
}