package ru.vldf.sportsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonalPageController {

    @GetMapping(value = {"/personalpage"})
    public String personalPage(ModelMap map) {
        return "personalpage";
    }
}
