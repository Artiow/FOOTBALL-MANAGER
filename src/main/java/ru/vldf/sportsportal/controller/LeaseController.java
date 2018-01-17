package ru.vldf.sportsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaseController {

    @GetMapping(value = {"/lease"})
    public String leasePage(ModelMap map) {
        return "lease";
    }
}