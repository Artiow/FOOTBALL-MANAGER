package ru.vldf.sportsportal.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.domain.common.UserEntity;

import java.util.Collection;

public class SecurityPrincipal extends User {
    private UserDTO userDTO;

    SecurityPrincipal(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getLogin(), user.getPassword(), authorities);
        userDTO = new UserDTO(user);
    }

    SecurityPrincipal(UserEntity user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(user.getLogin(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        userDTO = new UserDTO(user);
    }

    public UserDTO getUser() {
        return userDTO;
    }
}
