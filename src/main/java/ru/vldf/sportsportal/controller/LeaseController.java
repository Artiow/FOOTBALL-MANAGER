package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vldf.sportsportal.controller.security.SecurityController;
import ru.vldf.sportsportal.dao.impl.user.UserDAO;
import ru.vldf.sportsportal.model.playground.PlaygroundEntity;
import ru.vldf.sportsportal.model.playground.PlaygroundSpecializationEntity;
import ru.vldf.sportsportal.service.PlaygroundService;
import ru.vldf.sportsportal.service.security.SecurityPrincipal;

import java.util.Collection;
import java.util.List;

@Controller
public class LeaseController extends SecurityController {

    private PlaygroundService playgroundService;

    @Autowired
    public void setPlaygroundService(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
    }

    @GetMapping(value = {"/lease"})
    public String leasePage(ModelMap map) {
        SecurityPrincipal principal = getPrincipal();

        String name;
        if (principal != null) name = principal.getUser().getName() + " " + principal.getUser().getSurname();
        else name = "ERROR";
        map.addAttribute("name", name);

        List<PlaygroundEntity> list = playgroundService.listPlaygrounds(false);
        map.addAttribute("playgrounds", list);

        return "lease";
    }

    @GetMapping(value = {"/lease/{id}"})
    public String playgroundPage(@PathVariable("id") int id, ModelMap map) {
        SecurityPrincipal principal = getPrincipal();

        String name;
        if (principal != null) name = principal.getUser().getName() + " " + principal.getUser().getSurname();
        else name = "ERROR";
        map.addAttribute("name", name);

        PlaygroundEntity playground = playgroundService.getPlaygroundByID(id);
        map.addAttribute("playground", playground);

        return "leaseitem";
    }
}