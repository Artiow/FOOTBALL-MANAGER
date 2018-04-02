package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyStatusDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.RoleDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyDTO;
import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;
import ru.vldf.sportsportal.model.tourney.TeamTourneyStatusEntity;
import ru.vldf.sportsportal.model.user.RoleEntity;
import ru.vldf.sportsportal.model.user.UserEntity;
import ru.vldf.sportsportal.service.security.SecurityPrincipal;

@Service
public class AuthService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserDAO userDAO;
    private RoleDAO roleDAO;

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void register(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        RoleEntity role = roleDAO.findByCode("ROLE_UNCONFIRMED_USER"); //TODO: remove this in DAO

        userDAO.saveUser(new UserEntity(userDTO, role));
    }

    public UserDTO getAuthUser() {
        final String ROLE_ANONYMOUS = "anonymousUser";

        Object principal = getAuthentication().getPrincipal();
        if ((principal == null) || (principal.equals(ROLE_ANONYMOUS))) return null;
        else return ((SecurityPrincipal) principal).getUser();
    }

    public String getAuthUsername() {
        UserDTO user = getAuthUser();

        if (user != null) return user.toString();
        else return "ERROR";
    }
}