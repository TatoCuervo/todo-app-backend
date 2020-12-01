package com.tatocuervo.todoappbackend.services;

import com.tatocuervo.todoappbackend.common.exception.ResourceNotFoundException;
import com.tatocuervo.todoappbackend.model.Todo;
import com.tatocuervo.todoappbackend.model.User;
import com.tatocuervo.todoappbackend.repositories.TodoRepository;
import com.tatocuervo.todoappbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Todo> getTodosByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new ResourceNotFoundException(format("User with id %d does not exists", userId));

        return todoRepository.findAllByUserId(userId);
    }


    public void addTodoByUser(Todo todo, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(format("User with Id %d does not exists", userId)));
        todo.setUser(user);
        todoRepository.save(todo);
    }

    public void updateTodo(long id, Todo updatedTodo) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic with Id %d does not exists", id)));

        todo.setDescription(updatedTodo.getDescription());
        todo.setDone(updatedTodo.isDone());
        todo.setTargetDate(updatedTodo.getTargetDate());
        todoRepository.save(todo);
    }

    public void deleteTodo(long id) {
        todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic with Id %d does not exists", id)));

        todoRepository.deleteById(id);
    }
}
