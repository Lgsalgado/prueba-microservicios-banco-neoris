package com.prueba.cliente.config;

public class ClienteYaExisteException extends RuntimeException {
    public ClienteYaExisteException(String message) {
        super(message);
    }
}