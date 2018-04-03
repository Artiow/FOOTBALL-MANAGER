package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vldf.sportsportal.dto.tourney.TeamDTO;
import ru.vldf.sportsportal.dto.tourney.TeamPlayerDTO;
import ru.vldf.sportsportal.dto.user.UserDTO;
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
    public String toPersonalPage(ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUsername())
                .addAttribute("authUser", authService.getAuthUser());

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

    @GetMapping(value = {"/pp/admin/unconfirmed-user"})
    public String toConfirmUserPage(ModelMap map) {
        int num = adminService.getUnconfirmedUsersNum();
        if (num == 0) return "redirect:/pp/admin";

        UserDTO user = adminService.getFirstUnconfirmedUser();
        String id = user.getId().toString();

        return "redirect:/pp/admin/unconfirmed-user/user" + id;
    }

    @GetMapping(value = {"/pp/admin/unconfirmed-user/user{id}"})
    public String toConfirmUserPageByUser(@PathVariable("id") int id, ModelMap map) {
        map.addAttribute("username", authService.getAuthUsername());

        UserDTO user = adminService.getUser(id);
        List<TeamPlayerDTO> duplicates = adminService.getDuplicate(user);

        map
                .addAttribute("unconfirmedUser", user)
                .addAttribute("duplicates", duplicates);

        return "user/admin/unconfirmed-user";
    }

    @GetMapping(value = {"/pp/admin/unconfirmed-user/user{id}/confirm"})
    public String confirmUser(@PathVariable("id") int id) {
        adminService.confirmUser(id);
        return "redirect:/pp/admin/unconfirmed-user";
    }

    @GetMapping(value = {"/pp/admin/unconfirmed-user/user{id}/reject"})
    public String rejectUser(@PathVariable("id") int id) {
        adminService.rejectUser(id);
        return "redirect:/pp/admin/unconfirmed-user";
    }

    @GetMapping(value = {"/pp/admin/unconfirmed-user/duplicate{id}/delete"})
    public String deleteTeamPlayer(@PathVariable("id") int id) {
        adminService.deleteDuplicate(id);
        return "redirect:/pp/admin/unconfirmed-user";
    }

//    ==================================================================================
//    === TOURNEY

    @GetMapping(value = {"/pp/tourney"})
    public String toTourneyPage(ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUsername())
                .addAttribute("teamList", userService.getTeamList());

        return "user/tourney/tourneypage";
    }

    @GetMapping(value = {"/pp/tourney/create-team"})
    public String toCreateTeamPage(ModelMap map) {
        map.addAttribute("team", new TeamDTO());
        return "user/tourney/create-team";
    }

    @PostMapping(value = {"/pp/tourney/create-team"})
    public String createTeam(@ModelAttribute(value="team") TeamDTO teamDTO) {
        userService.createTeam(teamDTO);
        return "redirect:/pp/tourney";
    }
}