package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.dto.tourney.TourneyDTO;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.TourneyService;

import java.util.List;

@Controller
public class TourneyController {
    private AuthService authService;
    private TourneyService tourneyService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setTourneyService(TourneyService tourneyService) {
        this.tourneyService = tourneyService;
    }

    @ModelAttribute("authUser")
    public UserDTO getAuthUser() {
        return authService.getAuthUser();
    }

    @GetMapping(value = {"/tourney"})
    public String toCoverPage(ModelMap map) {
        return "tourney/cover";
    }

    @GetMapping(value = {"/tourney/t{id}"})
    public String toTourneyPage(@PathVariable("id") int id, ModelMap map) {
        map
                .addAttribute("tourneyList", tourneyService.getTourneyList())
                .addAttribute("tourneyCur", tourneyService.getTourney(id));

        switch (id) {
            case 3:
                return "tourney/cover-tourney-4";
            case 4:
                return "tourney/cover-tourney-2a";
            case 5:
                return "tourney/cover-tourney-2b";
            case 6:
                return "tourney/cover-tourney-3a";
            case 7:
                return "tourney/cover-tourney-3b";
            case 8:
                return "tourney/cover-tourney-1";
            default:
                return "tourney/cover-tourney-none";
        }
    }
}