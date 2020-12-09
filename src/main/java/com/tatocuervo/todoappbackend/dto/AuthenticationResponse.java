package com.tatocuervo.todoappbackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class AuthenticationResponse {
    private final String jwtToken;

    @JsonCreator
    public AuthenticationResponse(@JsonProperty String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
