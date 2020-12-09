package com.tatocuervo.todoappbackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class AuthenticationRequest {
    private final String username;
    private final String password;

    @JsonCreator
    public AuthenticationRequest(@JsonProperty String username, @JsonProperty String password) {
        this.username = username;
        this.password = password;
    }
}
