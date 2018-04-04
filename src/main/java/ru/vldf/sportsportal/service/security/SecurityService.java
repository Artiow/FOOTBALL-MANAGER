package ru.vldf.sportsportal.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.model.user.UserRoleEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SecurityService implements UserDetailsService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userDAO.findByLogin(s);
        return new SecurityPrincipal(user, buildUserAuthorities(user.getRole()));
    }

    private Collection<GrantedAuthority> buildUserAuthorities(UserRoleEntity role) {
        Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();

//        TODO: add multiple roles!
        auth.add(new SimpleGrantedAuthority(role.getCode()));
        return auth;
    }
}