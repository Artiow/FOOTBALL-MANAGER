package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vldf.sportsportal.service.LeaseService;
import ru.vldf.sportsportal.service.UserService;

@Controller
public class LeaseController {
    private UserService userService;
    private LeaseService leaseService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setLeaseService(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @GetMapping(value = {"/lease"})
    public String leasePage(ModelMap map) {
        userService.setAuthUserIn(map, "name");
        map.addAttribute("playgrounds", leaseService.getPlaygroundList());

        return "lease";
    }

    @GetMapping(value = {"/lease/pg{id}"})
    public String playgroundPage(@PathVariable("id") int id, ModelMap map) {
        userService.setAuthUserIn(map, "name");
        map.addAttribute("playground", leaseService.getPlayground(id));

        return "leaseitem";
    }
}