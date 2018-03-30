package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyDTO;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.UserService;

@Controller
public class UserController {
    private AuthService authService;
    private UserService userService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/personalpage"})
    public String personalPage(ModelMap map) {
        map.addAttribute("username", authService.getAuthUsername());
        map
                .addAttribute("teamTourney", new TeamTourneyDTO());

        return "auth/personalpage";
    }

    @PostMapping(value = {"/create-team"})
    public String register(@ModelAttribute(value="teamTourney") TeamTourneyDTO teamTourneyDTO) {
        userService.createTeam(teamTourneyDTO);
        return "redirect:/personalpage";
    }
}
