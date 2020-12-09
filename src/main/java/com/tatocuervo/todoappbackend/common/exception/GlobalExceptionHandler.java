package com.tatocuervo.todoappbackend.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return buildResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidUserCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserCredentialsException(InvalidUserCredentialsException exception) {
        return buildResponseEntity(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(String message, HttpStatus status) {
        ErrorResponse errorMessage = new ErrorResponse(message);
        return new ResponseEntity<>(errorMessage, status);
    }
}
