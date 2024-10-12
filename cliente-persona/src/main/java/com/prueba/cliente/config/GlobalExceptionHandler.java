package com.prueba.cliente.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteYaExisteException.class)
    public ResponseEntity<String> handleClienteYaExisteException(ClienteYaExisteException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // 409 Conflict
    }

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<String> handleClienteNoEncontradoException(ClienteNoEncontradoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @ExceptionHandler(PersonaNoEncontradaException.class)
    public ResponseEntity<String> handlePersonaNoEncontradaException(PersonaNoEncontradaException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
    }

}
