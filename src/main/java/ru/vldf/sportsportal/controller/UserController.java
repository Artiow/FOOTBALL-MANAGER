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
                .addAttribute("username", authService.getAuthUserShortName())
                .addAttribute("authUser", authService.getAuthUser());

        return "user/personalpage";
    }

//    ==================================================================================
//    === ADMIN

    @GetMapping(value = {"/pp/admin"})
    public String toAdminPage(ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUserShortName())
                .addAttribute("numOfUnconfirmedUsers", adminService.getUnconfirmedUsersNum())
                .addAttribute("numOfUnconfirmedTeams", adminService.getUnconfirmedTeamsNum());

        return "user/admin/adminpage";
    }

//    ----------------------------------------------------------------------------------
//    --- USER

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
        map.addAttribute("username", authService.getAuthUserShortName());

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

//    ----------------------------------------------------------------------------------
//    --- TOURNEY

    @GetMapping(value = {"/pp/admin/unconfirmed-teams"})
    public String toConfirmTeamPage(ModelMap map) {
        int num = adminService.getUnconfirmedTeamsNum();
        if (num == 0) return "redirect:/pp/admin";

        map
                .addAttribute("username", authService.getAuthUserShortName())
                .addAttribute("teams", adminService.getUnconfirmedTeams());

        return "user/admin/unconfirmed-teams";
    }

    @GetMapping(value = {"/pp/admin/unconfirmed-teams/team{id}/confirm"})
    public String confirmTeam(@PathVariable("id") int id) {
        adminService.confirmTeam(id);
        return "redirect:/pp/admin/unconfirmed-teams";
    }

    @GetMapping(value = {"/pp/admin/unconfirmed-teams/team{id}/reject"})
    public String rejectTeam(@PathVariable("id") int id) {
        adminService.rejectTeam(id);
        return "redirect:/pp/admin/unconfirmed-teams";
    }

//    ==================================================================================
//    === TOURNEY

    @GetMapping(value = {"/pp/tourney"})
    public String toTourneyPage(ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUserShortName())
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

    @GetMapping(value = {"/pp/tourney/team{id}"})
    public String toTeamPage(@PathVariable("id") int id, ModelMap map) {
        map
                .addAttribute("username", authService.getAuthUserShortName())
                .addAttribute("team", userService.getTeamByIDForAuthUser(id));

        return "user/tourney/teampage";
    }
}