package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.user.UserRoleDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.domain.user.UserEntity;
import ru.vldf.sportsportal.service.security.SecurityPrincipal;

@Service
public class AuthService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Transactional
    public void register(UserDTO userDTO) {
        String ROLE_UNCONFIRMED_CODE = "ROLE_UNCONFIRMED";
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        userDAO.save(
                new UserEntity(
                        userDTO,
                        userRoleDAO.findByCode(ROLE_UNCONFIRMED_CODE)
                )
        );
    }

//    TODO: get by HttpServletRequest
    public UserDTO getAuthUser() {
        final String ROLE_ANONYMOUS = "anonymousUser";

        Object principal = getAuthentication().getPrincipal();
        if ((principal == null) || (principal.equals(ROLE_ANONYMOUS))) return null;
        else return ((SecurityPrincipal) principal).getUser();
    }

    public String getAuthUserShortName() {
        UserDTO user = getAuthUser();
        if (user != null) return (user.getName() + ' ' + user.getSurname());
        else return "ERROR";
    }
}