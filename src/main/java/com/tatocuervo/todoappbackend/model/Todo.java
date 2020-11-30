package com.tatocuervo.todoappbackend.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity(name = "TODO")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", nullable = false, updatable = false)
    private User user;

    private String description;

    @Column(name = "target_date")
    private Date targetDate;

    private boolean done;
}
