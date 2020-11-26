package com.tatocuervo.todoappbackend.services;

import com.tatocuervo.todoappbackend.model.Todo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {
    public List<Todo> getTodosByUsername(String username) {
        return Arrays.asList(Todo.builder()
                .id(1)
                .description("description")
                .isDone(false)
                .username("tato")
                .targetDate(new Date())
                .build()
        );
    }

    public void addTodoByUser(Todo todo, String user) {

    }

    public void updateTodo(long id, Todo updatedTodo) {

    }

    public void deleteTodo(long id) {

    }
}
