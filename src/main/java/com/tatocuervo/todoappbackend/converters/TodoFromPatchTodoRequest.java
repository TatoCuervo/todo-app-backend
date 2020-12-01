package com.tatocuervo.todoappbackend.converters;

import com.tatocuervo.todoappbackend.dto.PatchTodoRequest;
import com.tatocuervo.todoappbackend.model.Todo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TodoFromPatchTodoRequest implements Converter<PatchTodoRequest, Todo> {
    @Override
    public Todo convert(PatchTodoRequest patchTodoRequest) {
        return Todo.builder()
                .description(patchTodoRequest.getDescription())
                .targetDate(patchTodoRequest.getTargetDate())
                .done(patchTodoRequest.isDone())
                .build();
    }
}
