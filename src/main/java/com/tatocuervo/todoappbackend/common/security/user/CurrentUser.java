package com.tatocuervo.todoappbackend.common.security.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class CurrentUser extends User {

    @Setter
    private long userId;

    public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public CurrentUser(String name, String password, ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities) {
        super(name, password, simpleGrantedAuthorities);
    }
}
