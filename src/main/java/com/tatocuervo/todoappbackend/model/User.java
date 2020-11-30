package com.tatocuervo.todoappbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "USERS")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todoList;
}
