package com.tatocuervo.todoappbackend.controllers;

import com.tatocuervo.todoappbackend.model.Todo;
import com.tatocuervo.todoappbackend.routes.Routes;
import com.tatocuervo.todoappbackend.services.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;

@Api(tags = "Todos")
@RestController
@RequestMapping(path = Routes.TODO)
public class TodoController {

    @Autowired
    private TodoService service;

    @ApiOperation(value = "Get all TODOs by user")
    @GetMapping
    public List<Todo> getTodosByUser(@PathVariable Long userId) {
        return service.getTodosByUserId(userId);
    }

    @ApiOperation(value = "Add todo by user")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTodoByUser(@PathVariable String user, @RequestBody Todo todo) {
        service.addTodoByUser(todo, user);
        return ResponseEntity.created(URI.create(format("/%s/todo/%d", user, todo.getId()))).build();
    }

    @ApiOperation(value = "Update todo by id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable long id, @RequestBody Todo todo) {
        service.updateTodo(id, todo);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Delete todo by id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        service.deleteTodo(id);
        return ResponseEntity.ok().build();
    }
}
