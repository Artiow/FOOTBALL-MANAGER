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
    public String toLoginPage(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        map.addAttribute("error", (error != null));
        return "auth/login";
    }

    @GetMapping(value = {"/registration"})
    public String toRegisterPage(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        map.addAttribute("error", (error != null));

        UserDTO user = new UserDTO();
        map.addAttribute("userDTO", user); //TODO remove this!

        return "auth/registration";
    }

    @PostMapping(value = {"/registration"})
    public String register(@ModelAttribute(value="userDTO") UserDTO user) {
        //TODO: confirm password check!
        authService.register(user);

        //TODO: add various error message!
        return "redirect:/login";
    }
}