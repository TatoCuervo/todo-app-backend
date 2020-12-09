package com.tatocuervo.todoappbackend.controllers;

import com.tatocuervo.todoappbackend.routes.Routes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Authentication")
@RestController()
@RequestMapping(path = Routes.AUTHENTICATE)
public class AuthController {

    @ApiOperation(value = "Authenticate and issue JWT token")
    @PostMapping
    public ResponseEntity<Void> authenticate() {
        return ResponseEntity.ok().build();
    }
}
