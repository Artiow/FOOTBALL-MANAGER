package ru.vldf.sportsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.ModelMap;
import ru.vldf.sportsportal.controller.security.SecurityController;
import ru.vldf.sportsportal.service.security.SecurityPrincipal;

@Controller
public class TmpControllers extends SecurityController {

    @GetMapping(value = {"/matches"})
    public String matchesPage(ModelMap map) {
        SecurityPrincipal principal = getPrincipal();

        String name;
        if (principal != null) name = principal.getUser().getName() + " " + principal.getUser().getSurname();
        else name = "ERROR";

        map.addAttribute("name", name);
        return "matches";
    }

    @GetMapping(value = {"/playgrounds"})
    public String playgroundsPage(ModelMap map) {
        SecurityPrincipal principal = getPrincipal();

        String name;
        if (principal != null) name = principal.getUser().getName() + " " + principal.getUser().getSurname();
        else name = "ERROR";

        map.addAttribute("name", name);
        return "playgrounds";
    }
}