package com.prueba.cliente.config;

public class PersonaNoEncontradaException extends RuntimeException {
    public PersonaNoEncontradaException(String message) {
        super(message);
    }
}
