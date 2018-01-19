package ru.vldf.sportsportal.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.vldf.sportsportal.dto.UserDTO;
import ru.vldf.sportsportal.model.RoleEntity;
import ru.vldf.sportsportal.model.UserEntity;
import ru.vldf.sportsportal.dao.impl.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceSecurity implements UserDetailsService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userDAO.findByEMail(s);
        return new UserPrincipal(user, buildUserAuthority(user.getRole()));
    }

    private List<GrantedAuthority> buildUserAuthority(RoleEntity role) {
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();

        auth.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));

        return auth;
    }
}