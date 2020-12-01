package com.tatocuervo.todoappbackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class PatchTodoRequest {
    private final String description;
    private final Date targetDate;
    private final boolean done;

    @JsonCreator
    public PatchTodoRequest(@JsonProperty String description, @JsonProperty Date targetDate, @JsonProperty boolean done) {
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }
}
