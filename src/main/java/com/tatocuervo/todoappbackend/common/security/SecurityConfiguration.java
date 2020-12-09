package com.tatocuervo.todoappbackend.common.security;

import com.tatocuervo.todoappbackend.routes.Routes;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static java.lang.String.format;

/**
 * Spring security configuration
 */

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers(buildPublicRoutes()).permitAll()
                .mvcMatchers(buildPrivateRoutes()).authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private String[] buildPublicRoutes() {
        return new String[]{
                format("/%s/**", Routes.AUTHENTICATE),
        };
    }

    private String[] buildPrivateRoutes() {
        return new String[]{
                format("/%s/**", Routes.BASE_PATH),
        };
    }
}
