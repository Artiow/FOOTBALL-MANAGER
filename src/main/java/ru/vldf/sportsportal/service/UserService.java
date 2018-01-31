package ru.vldf.sportsportal.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import ru.vldf.sportsportal.dto.UserDTO;
import ru.vldf.sportsportal.service.security.SecurityPrincipal;

@Service
public class UserService {

//    ==================================================================================
//    === AUTH

    private final String ROLE_ANONYMOUS = "anonymousUser";

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public UserDTO getAuthUser() {
        Authentication auth = getAuthentication();

        Object principal = auth.getPrincipal();
        if ((principal == null) || (principal.equals(ROLE_ANONYMOUS))) return null;
        else return ((SecurityPrincipal) principal).getUser();
    }

    public String getAuthUsername() {
        UserDTO user = getAuthUser();

        if (user != null) return user.toString();
        else return "ERROR";
    }
}