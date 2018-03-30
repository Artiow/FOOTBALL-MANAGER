package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyDTO;
import ru.vldf.sportsportal.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/personalpage"})
    public String personalPage(ModelMap map) {
        map.addAttribute("username", userService.getAuthUsername());

        return "auth/personalpage";
    }

    @PostMapping(value = {"/create-team"})
    public String register(@ModelAttribute(value="user") TeamTourneyDTO teamTourneyDTO) {
        userService.createTeam(teamTourneyDTO);
        return "redirect:/personalpage";
    }
}
