package com.tatocuervo.todoappbackend.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Todo {
    private long id;
    private String username;
    private String description;
    private Date targetDate;
    private boolean isDone;
}
