package ru.vldf.sportsportal.controller.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.vldf.sportsportal.service.security.SecurityPrincipal;

public class SecurityController {
    protected SecurityPrincipal getPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal.equals("anonymousUser")) return null;
        else return (SecurityPrincipal) auth.getPrincipal();
    }
}
