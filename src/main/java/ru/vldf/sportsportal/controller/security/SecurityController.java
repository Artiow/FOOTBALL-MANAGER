package ru.vldf.sportsportal.controller.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.vldf.sportsportal.service.security.UserPrincipal;

public class SecurityController {
    protected UserPrincipal getPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal.equals("anonymousUser")) return null;
        else return (UserPrincipal) auth.getPrincipal();
    }
}
