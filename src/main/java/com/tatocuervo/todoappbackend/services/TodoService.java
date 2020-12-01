package com.tatocuervo.todoappbackend.services;

import com.tatocuervo.todoappbackend.model.Todo;
import com.tatocuervo.todoappbackend.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodosByUserId(Long userId) {
        return todoRepository.findAllByUserId(userId);
    }


    public void addTodoByUser(Todo todo, String user) {

    }

    public void updateTodo(long id, Todo updatedTodo) {

    }

    public void deleteTodo(long id) {

    }
}
