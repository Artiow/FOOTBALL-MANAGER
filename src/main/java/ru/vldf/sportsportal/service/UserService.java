package ru.vldf.sportsportal.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.vldf.sportsportal.model.RoleEntity;
import ru.vldf.sportsportal.model.UserEntity;
import ru.vldf.sportsportal.dao.impl.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    public List<UserEntity> listUsers() {
        return userDAO.list();
    }

//    ==================================================================================
//    === AUTH

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userDAO.findByEMail(s);

        return buildUserAuthentication(user, buildUserAuthority(user.getRole()));
    }

    private List<GrantedAuthority> buildUserAuthority(RoleEntity role) {
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();

        auth.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));

        return auth;
    }

    private User buildUserAuthentication(UserEntity user, List<GrantedAuthority> authorities) {
        return new User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}