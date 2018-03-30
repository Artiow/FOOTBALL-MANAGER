package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vldf.sportsportal.service.AuthService;

@Controller
public class TourneyController {
    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = {"/tourney"})
    public String coverPage(ModelMap map) {
        map.addAttribute("username", authService.getAuthUsername());

        return "tourney/cover";
    }
}