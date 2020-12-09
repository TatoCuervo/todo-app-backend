package com.tatocuervo.todoappbackend.common.security;

import com.tatocuervo.todoappbackend.common.security.filter.JwtTokenAuthorizationFilter;
import com.tatocuervo.todoappbackend.routes.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static java.lang.String.format;

/**
 * Spring security configuration
 */

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenAuthorizationFilter jwtTokenAuthorizationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers(buildPublicRoutes()).permitAll()
                .mvcMatchers(buildPrivateRoutes()).authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // do not create session

        http.addFilterBefore(jwtTokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class); // add filter
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
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
