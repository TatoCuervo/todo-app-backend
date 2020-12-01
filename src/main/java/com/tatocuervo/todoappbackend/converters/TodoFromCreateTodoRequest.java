package com.tatocuervo.todoappbackend.converters;

import com.tatocuervo.todoappbackend.dto.CreateTodoRequest;
import com.tatocuervo.todoappbackend.model.Todo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TodoFromCreateTodoRequest implements Converter<CreateTodoRequest, Todo> {

    @Override
    public Todo convert(CreateTodoRequest createTodoRequest) {
        return Todo.builder()
                .description(createTodoRequest.getDescription())
                .targetDate(createTodoRequest.getTargetDate())
                .done(createTodoRequest.isDone())
                .build();
    }
}
