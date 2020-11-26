package com.tatocuervo.todoappbackend.controllers;

import com.tatocuervo.todoappbackend.model.Todo;
import com.tatocuervo.todoappbackend.routes.Routes;
import com.tatocuervo.todoappbackend.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = Routes.TODO)
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping
    public List<Todo> getTodosByUser(@PathVariable String user) {
        return service.getTodosByUsername(user);
    }

    @PostMapping
    public ResponseEntity<Void> addTodoByUser(@PathVariable String user, @RequestBody Todo todo) {
        service.addTodoByUser(todo, user);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable long id, @RequestBody Todo todo) {
        service.updateTodo(id, todo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        service.deleteTodo(id);
        return ResponseEntity.ok().build();
    }
}
