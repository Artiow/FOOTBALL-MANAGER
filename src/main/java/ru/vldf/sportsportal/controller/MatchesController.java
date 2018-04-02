package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vldf.sportsportal.service.AuthService;

@Controller
public class MatchesController {
    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = {"/matches"})
    public String toCoverPage(ModelMap map) {
        map.addAttribute("username", authService.getAuthUsername());

        return "matches/cover";
    }
}