package ru.vldf.sportsportal.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.user.specialized.TourneyUserService;

@Controller
public class UserController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

//    ================================================
//    TODO: tmp solution! remove this!
    private TourneyUserService userTourneyService;

    @Autowired
    public void setUserTourneyService(TourneyUserService userTourneyService) {
        this.userTourneyService = userTourneyService;
    }
//    ================================================

    @ModelAttribute("authUser")
    public UserDTO getAuthUser() {
        return authService.getAuthUser();
    }


    @GetMapping(value = {"/personalpage", "/pp"})
    public String toPersonalPage(ModelMap map) {
//        ============================================
//        TODO: tmp solution! remove this!
        map.addAttribute("teamList", userTourneyService.getTeamList());
//        ============================================

        return "user/personalpage";
    }
}