package ru.vldf.sportsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.ModelMap;

@Controller
public class TmpController {

    @GetMapping(value = {"/lease"})
    public String leasePage(ModelMap map) {
        return "lease";
    }

    @GetMapping(value = {"/matches"})
    public String matchesPage(ModelMap map) {
        return "matches";
    }

    @GetMapping(value = {"/playgrounds"})
    public String playgroundsPage(ModelMap map) {
        return "playgrounds";
    }

    @GetMapping(value = {"/personalpage"})
    public String personalPage(ModelMap map) {
        return "personalpage";
    }
}