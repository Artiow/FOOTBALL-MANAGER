package ru.vldf.sportsportal.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.dto.tourney.PlayerDTO;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.admin.AdminService;
import ru.vldf.sportsportal.service.admin.specialized.TourneyAdminService;

import java.util.List;

@Controller
public class AdminController {
    private AuthService authService;
    private AdminService adminService;
    private TourneyAdminService tourneyAdminService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setTourneyAdminService(TourneyAdminService tourneyAdminService) {
        this.tourneyAdminService = tourneyAdminService;
    }


    @ModelAttribute("authUser")
    public UserDTO getAuthUser() {
        return authService.getAuthUser();
    }


    @GetMapping(value = {"/pp/admin"})
    public String toAdminMenu(ModelMap map) {
        map
                .addAttribute("uUsersNum", adminService.getUnconfirmedUsersNum())
                .addAttribute("uTeamsNum", tourneyAdminService.getUnconfirmedTeamsNum());

        return "user/admin/menu-admin";
    }

//    ===========================================================
//    === USER MANAGEMENT

    @GetMapping(value = {"/pp/admin/check-user"})
    public String toCheckUserPage(ModelMap map) {
        UserDTO user = adminService.getFirstUnconfirmedUser(); //TODO: lol wtf dude? add pagination!
        if (user == null) return "redirect:/pp/admin";

        List<PlayerDTO> duplicates
                = adminService.getDuplicates(user);
        map
                .addAttribute("uUser", user)
                .addAttribute("duplicates", duplicates);

        return "user/admin/page-check-user";
    }

    @GetMapping(value = {"/pp/admin/check-user/user{id}"})
    public String toCheckUserPage(@PathVariable("id") int id, ModelMap map) {
        UserDTO user = adminService.getUser(id);
        if (user == null) return "redirect:/404";

        List<PlayerDTO> duplicates
                = adminService.getDuplicates(user);
        map
                .addAttribute("uUser", user)
                .addAttribute("duplicates", duplicates);

        return "user/admin/page-check-user";
    }


    @GetMapping(value = {"/pp/admin/check-user/user{id}/confirm"})
    public String confirmUser(@PathVariable("id") int id) {
        adminService.confirmUser(id);
        return "redirect:/pp/admin/check-user";
    }

    @GetMapping(value = {"/pp/admin/check-user/user{id}/reject"})
    public String rejectUser(@PathVariable("id") int id) {
        adminService.rejectUser(id);
        return "redirect:/pp/admin/check-user";
    }

    @GetMapping(value = {"/pp/admin/check-user/duplicate{id}/delete"})
    public String deleteDuplicate(@PathVariable("id") int id) {
        adminService.deleteDuplicate(id);
        return "redirect:/pp/admin/check-user";
    }

    @GetMapping(value = "/pp/admin/check-user/user{userID}/bind/duplicate{duplicateID}")
    public String bindDuplicate(@PathVariable("userID") int userID, @PathVariable("duplicateID") int duplicateID) {
        adminService.bindUser(userID, duplicateID);
        return "redirect:/pp/admin/check-user";
    }
}
