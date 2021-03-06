package com.tatocuervo.todoappbackend.common.security;

import com.tatocuervo.todoappbackend.common.security.filter.JwtTokenAuthorizationFilter;
import com.tatocuervo.todoappbackend.routes.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers(buildPublicRoutes()).permitAll()
                .mvcMatchers(buildPrivateRoutes()).authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // do not create session

        http.headers().frameOptions().disable(); // to allow h2 console web access
        http.addFilterBefore(jwtTokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class); // add filter
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailService).passwordEncoder(passwordEncoderBean());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return NoOpPasswordEncoder.getInstance();
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
