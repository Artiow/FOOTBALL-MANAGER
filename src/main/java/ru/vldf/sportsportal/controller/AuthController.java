package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vldf.sportsportal.dto.UserDTO;
import ru.vldf.sportsportal.service.UserService;
import ru.vldf.sportsportal.service.security.SecurityService;

@Controller
public class AuthController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/login"})
    public String loginPage(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        map.addAttribute("error", (error != null));
        return "auth/login";
    }

    @GetMapping(value = {"/personalpage"})
    public String personalPage(ModelMap map) {
        map.addAttribute("username", userService.getAuthUsername());

        return "auth/personalpage";
    }

    @GetMapping(value = {"/registration"})
    public String registerPage(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        map.addAttribute("error", (error != null));

        UserDTO user = new UserDTO();
        map.addAttribute("user", user);

        return "auth/registration";
    }

    @PostMapping(value = {"/registration"})
    public String register(
            @ModelAttribute(value="user") UserDTO user
    ) {
        //TODO: confirm password!

        userService.registerUser(user);

        return "redirect:/index";
    }
}