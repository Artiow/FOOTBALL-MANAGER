package ru.vldf.sportsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vldf.sportsportal.controller.security.SecurityController;
import ru.vldf.sportsportal.service.security.UserPrincipal;

@Controller
public class LeaseController extends SecurityController {

    @GetMapping(value = {"/lease"})
    public String leasePage(ModelMap map) {
        UserPrincipal principal = getPrincipal();

        String name;
        if (principal != null) name = principal.getUser().getName() + " " + principal.getUser().getSurname();
        else name = "ERROR";

        map.addAttribute("name", name);
        return "lease";
    }
}