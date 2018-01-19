package ru.vldf.sportsportal.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vldf.sportsportal.dto.UserDTO;
import ru.vldf.sportsportal.service.security.UserPrincipal;

@Controller
public class PersonalPageController {

    @GetMapping(value = {"/personalpage"})
    public String personalPage(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        UserDTO user = userPrincipal.getUser();

        map.addAttribute("name", user.getName() + " " + user.getSurname());
        return "personalpage";
    }
}