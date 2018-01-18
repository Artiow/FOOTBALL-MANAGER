package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vldf.sportsportal.dao.impl.UserDAO;
import ru.vldf.sportsportal.model.UserEntity;
import ru.vldf.sportsportal.service.UserService;

import java.util.List;

@Controller
public class PersonalPageController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/personalpage"})
    public String personalPage(ModelMap map) {
        List<UserEntity> list = userService.listUsers();
        map.addAttribute("list", list);
        return "personalpage";
    }
}