package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vldf.sportsportal.dto.lease.PlaygroundDTO;
import ru.vldf.sportsportal.service.LeaseService;
import ru.vldf.sportsportal.service.AuthService;

import java.util.List;

@Controller
public class LeaseController {
    private AuthService authService;
    private LeaseService leaseService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setLeaseService(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @GetMapping(value = {"/lease"})
    public String catalogPage(ModelMap map) {
        map.addAttribute("username", authService.getAuthUsername());

        List<PlaygroundDTO> playgroundList = leaseService.getPlaygroundList();
        map.addAttribute("playgroundList", playgroundList);
        map.addAttribute("playgroundListSize", playgroundList.size());

        return "lease/catalog";
    }

    @GetMapping(value = {"/lease/pg{id}"})
    public String playgroundPage(@PathVariable("id") int id, ModelMap map) {
        map.addAttribute("username", authService.getAuthUsername());
        map.addAttribute("playground", leaseService.getPlayground(id));

        return "lease/playground";
    }
}