package com.prueba.cuenta.config;

public class CuentaYaExistenteException extends RuntimeException {
    public CuentaYaExistenteException(String message) {
        super(message);
    }
}