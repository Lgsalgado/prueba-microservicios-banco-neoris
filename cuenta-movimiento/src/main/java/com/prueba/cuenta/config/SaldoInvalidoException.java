package com.prueba.cuenta.config;

public class SaldoInvalidoException extends RuntimeException {
    public SaldoInvalidoException(String message) {
        super(message);
    }
}
