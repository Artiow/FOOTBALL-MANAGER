package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import ru.vldf.sportsportal.dao.generic.RoleDAO;
import ru.vldf.sportsportal.dao.generic.UserDAO;
import ru.vldf.sportsportal.dto.RoleDTO;
import ru.vldf.sportsportal.dto.UserDTO;
import ru.vldf.sportsportal.model.RoleEntity;
import ru.vldf.sportsportal.model.UserEntity;
import ru.vldf.sportsportal.service.security.SecurityPrincipal;

@Service
public class UserService {
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

    //    ==================================================================================
//    === AUTH

    private final String ROLE_ANONYMOUS = "anonymousUser";

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void registerUser(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        RoleEntity role = roleDAO.findByCode("ROLE_USER");

        userDAO.saveUser(new UserEntity(userDTO, role));
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