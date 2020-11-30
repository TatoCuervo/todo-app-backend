package com.tatocuervo.todoappbackend.services;

import com.tatocuervo.todoappbackend.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    public List<Todo> getTodosByUsername(String username) {
        return new ArrayList<>();
    }

    public void addTodoByUser(Todo todo, String user) {

    }

    public void updateTodo(long id, Todo updatedTodo) {

    }

    public void deleteTodo(long id) {

    }
}
