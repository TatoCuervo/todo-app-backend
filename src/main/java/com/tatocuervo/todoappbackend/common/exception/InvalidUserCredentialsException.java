package com.tatocuervo.todoappbackend.common.exception;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
        super("Incorrect username or password ");
    }
}
