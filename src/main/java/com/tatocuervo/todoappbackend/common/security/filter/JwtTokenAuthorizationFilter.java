package com.tatocuervo.todoappbackend.common.security.filter;

import com.tatocuervo.todoappbackend.common.jwt.JwtUtil;
import com.tatocuervo.todoappbackend.common.security.JwtUserDetailService;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Verifies every authenticated API request holds a valid JWT token
 */

@Component
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("doFilterInternal");
        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");

        if (requestTokenHeader != null && requestTokenHeader.startsWith(JwtUtil.JWT_TOKEN_PREFIX)) {
            String jwt = requestTokenHeader.substring(JwtUtil.JWT_TOKEN_PREFIX.length());

            try {
                if (jwtUtil.validateToken(jwt)) {
                    // load user details from DB
                    UserDetails userDetails = jwtUserDetailService.loadUserByUsername(jwtUtil.getUsernameFromToken(jwt));

                    // create spring authentication
                    Authentication authentication = createAuthentication(userDetails, httpServletRequest);

                    // set authenticated user to spring context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JwtException | IllegalArgumentException e) {
                logger.warn("Provided JWT token is invalid or expired");
                SecurityContextHolder.clearContext();
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Provided JWT token is invalid or expired");
            }
        }

        // resume filters chain execution
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private Authentication createAuthentication(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return usernamePasswordAuthenticationToken;
    }
}
