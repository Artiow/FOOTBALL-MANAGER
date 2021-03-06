package ru.vldf.sportsportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.common.UserDAO;
import ru.vldf.sportsportal.domain.common.UserRoleEntity;
import ru.vldf.sportsportal.domain.common.UserEntity;

import java.util.HashSet;
import java.util.Set;

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

    private Set<GrantedAuthority> buildUserAuthorities(UserRoleEntity role) {
        Set<GrantedAuthority> auth = new HashSet<GrantedAuthority>();

//        TODO: add multiple roles!
        auth.add(new SimpleGrantedAuthority(role.getCode()));
        return auth;
    }
}