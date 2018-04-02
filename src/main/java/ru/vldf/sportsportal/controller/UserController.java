package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyConfirmDTO;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyDTO;
import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.service.AdminService;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.UserService;

import java.util.ArrayList;

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
    public String toPersonalPage(ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUsername())
                .addAttribute("user", authService.getAuthUser());

        return "user/personalpage";
    }

//    ==================================================================================
//    === ADMIN

    @GetMapping(value = {"/pp/admin"})
    public String toAdminPage(ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUsername())
                .addAttribute("numOfUnconfirmedUsers", adminService.getUnconfirmedUsersNum());

        return "user/admin/adminpage";
    }

    @GetMapping(value = {"/pp/admin/confirm-user"})
    public String toConfirmPage(ModelMap map) {
        int num = adminService.getUnconfirmedUsersNum();
        if (num == 0) return "redirect:/pp/admin";

        UserDTO user = adminService.getFirstUnconfirmedUser();
        String id = user.getId().toString();

        return "redirect:/pp/admin/confirm-user/user" + id;
    }

    @GetMapping(value = {"/pp/admin/confirm-user/user{id}"})
    public String toConfirmPageByUser(@PathVariable("id") int id, ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUsername())
                .addAttribute("unconfirmedUser", adminService.getUser(id));

        return "user/admin/confirm-user";
    }

    @PostMapping(value = {"/pp/admin/confirm-user/user{id}"})
    public String confirmUser(@PathVariable("id") int id) {
        adminService.confirmUser(id);
        return "redirect:/pp/admin/confirm-user";
    }

//    ==================================================================================
//    === TOURNEY

//    TODO: redesign!

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