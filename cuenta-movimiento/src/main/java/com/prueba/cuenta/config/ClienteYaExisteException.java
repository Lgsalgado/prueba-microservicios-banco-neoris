package com.prueba.cuenta.config;

public class ClienteYaExisteException extends RuntimeException {
    public ClienteYaExisteException(String message) {
        super(message);
    }
}