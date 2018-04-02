package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyConfirmDTO;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyDTO;
import ru.vldf.sportsportal.service.AdminService;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private AuthService authService;

    private AdminService adminService;
    private UserService userService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/personalpage"})
    public String personalPage(ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUsername())
                .addAttribute("user", authService.getAuthUser());

        return "user/personalpage";
    }

//    ==================================================================================
//    === ADMIN

    @GetMapping(value = {"/pp/admin"})
    public String adminPage(ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUsername())
                .addAttribute("team_tourney_list", adminService.getAwaitingTeamTourneyList());

        return "user/adminpage";
    }

    @PostMapping(value = {"/pp/tourney/confirm-team-tourney"})
    public String confirmTeamTourney(@ModelAttribute(value="team_tourney_list") ArrayList<TeamTourneyConfirmDTO> listTeamTourneyConfirmDTO) {
        adminService.confirmAwaitingTeamTourneyList(listTeamTourneyConfirmDTO);
        return "redirect:/pp/admin";
    }

//    ==================================================================================
//    === TOURNEY

    @GetMapping(value = {"/pp/tourney"})
    public String tourneyPage(ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUsername())
                .addAttribute("team_tourney_list", userService.getTeamTourneyList());

        return "user/tourneypage";
    }

    @GetMapping(value = {"/pp/tourney/create-team-tourney"})
    public String createTeamTourneyPage(ModelMap map) {
        map.addAttribute("team_tourney", new TeamTourneyDTO());
        return "user/create-team-tourney";
    }

    @PostMapping(value = {"/pp/tourney/create-team-tourney"})
    public String createTeamTourney(@ModelAttribute(value="team_tourney") TeamTourneyDTO teamTourneyDTO) {
        userService.createTeamTourney(teamTourneyDTO);
        return "redirect:/pp/tourney";
    }
}