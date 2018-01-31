package ru.vldf.sportsportal.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.vldf.sportsportal.dto.UserDTO;
import ru.vldf.sportsportal.model.UserEntity;

import java.util.Collection;

public class SecurityPrincipal extends User {
    private UserDTO userDTO;

    public SecurityPrincipal(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        userDTO = new UserDTO(user);
    }

    public SecurityPrincipal(UserEntity user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        userDTO = new UserDTO(user);
    }

    public UserDTO getUser() {
        return userDTO;
    }
}
