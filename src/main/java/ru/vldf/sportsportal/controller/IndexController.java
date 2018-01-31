package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vldf.sportsportal.service.UserService;

@Controller
public class IndexController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", "/index"})
    public String indexPage(ModelMap map) {
        userService.setAuthUserIn(map, "name");

        return "index";
    }
}