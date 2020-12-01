package com.tatocuervo.todoappbackend.controllers;

import com.tatocuervo.todoappbackend.dto.CreateTodoRequest;
import com.tatocuervo.todoappbackend.dto.PatchTodoRequest;
import com.tatocuervo.todoappbackend.model.Todo;
import com.tatocuervo.todoappbackend.routes.Routes;
import com.tatocuervo.todoappbackend.services.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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

    @Autowired
    private ConversionService conversionService;

    @ApiOperation(value = "Get all TODOs by user")
    @GetMapping
    public List<Todo> getTodosByUser(@PathVariable Long userId) {
        return service.getTodosByUserId(userId);
    }

    @ApiOperation(value = "Add todo by user")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addTodoByUser(@PathVariable Long userId, @RequestBody CreateTodoRequest createTodoRequest) {
        service.addTodoByUser(conversionService.convert(createTodoRequest, Todo.class), userId);
        return ResponseEntity.created(URI.create(format("/%d/todo", userId))).build();
    }

    @ApiOperation(value = "Update todo by id")
    @PatchMapping(path = "/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable long id, @RequestBody PatchTodoRequest patchTodoRequest) {
        service.updateTodo(id, conversionService.convert(patchTodoRequest, Todo.class));
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Delete todo by id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        service.deleteTodo(id);
        return ResponseEntity.ok().build();
    }
}
