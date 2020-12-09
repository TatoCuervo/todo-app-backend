package com.tatocuervo.todoappbackend.controllers;

import com.tatocuervo.todoappbackend.common.exception.InvalidUserCredentialsException;
import com.tatocuervo.todoappbackend.common.jwt.JwtUtil;
import com.tatocuervo.todoappbackend.common.security.user.CurrentUser;
import com.tatocuervo.todoappbackend.dto.AuthenticationRequest;
import com.tatocuervo.todoappbackend.dto.AuthenticationResponse;
import com.tatocuervo.todoappbackend.routes.Routes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Authentication")
@RestController()
@RequestMapping(path = Routes.AUTHENTICATE)
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @ApiOperation(value = "Authenticate and issue JWT token")
    @PostMapping
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        // verify credentials are valid, throw exception otherwise
        authenticate(authenticationRequest);

        // retrieve user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        // generate jwt token
        String jwt = jwtUtil.generateToken((CurrentUser) userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping(path = "/refresh")
    public ResponseEntity<?> refreshToken() {
        //TODO: implement token refresh feature
        return ResponseEntity.ok().build();
    }

    private void authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException();
        }
    }
}
