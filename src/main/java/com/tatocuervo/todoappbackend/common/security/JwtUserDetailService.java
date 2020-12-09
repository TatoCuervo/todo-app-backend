package com.tatocuervo.todoappbackend.common.security;

import com.tatocuervo.todoappbackend.model.AppUser;
import com.tatocuervo.todoappbackend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static java.lang.String.format;

/**
 * Implements UserDetailsService to perform user validation
 */

@Service
public class JwtUserDetailService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.debug("Attempt to find user with name {}", s);
        Optional<AppUser> optional = userRepository.findByName(s);

        if (optional.isEmpty()) {
            logger.warn("User with name {} does nos exists", s);
            throw new UsernameNotFoundException(format("User with name %s does nos exists", s));
        }

        AppUser appUser = optional.get();
        return new User(appUser.getName(), appUser.getPassword(), new ArrayList<SimpleGrantedAuthority>());
    }
}
