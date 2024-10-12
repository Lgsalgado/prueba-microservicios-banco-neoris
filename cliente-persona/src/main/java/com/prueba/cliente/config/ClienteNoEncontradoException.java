package com.prueba.cliente.config;

public class ClienteNoEncontradoException extends RuntimeException {
    public ClienteNoEncontradoException(String message) {
        super(message);
    }
}