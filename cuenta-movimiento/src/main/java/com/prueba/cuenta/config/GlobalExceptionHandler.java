package com.prueba.cuenta.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<String> handleSaldoInsuficiente(SaldoInsuficienteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleGeneralError(RuntimeException ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }
    @ExceptionHandler(CuentaNoEncontradaException.class)
    public ResponseEntity<String> handleCuentaNoEncontrada(CuentaNoEncontradaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CuentaYaExistenteException.class)
    public ResponseEntity<String> handleCuentaYaExistente(CuentaYaExistenteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // 409 Conflict
    }

    @ExceptionHandler(SaldoInvalidoException.class)
    public ResponseEntity<String> handleSaldoInvalido(SaldoInvalidoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ClienteYaExisteException.class)
    public ResponseEntity<String> handleClienteYaExisteException(ClienteYaExisteException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // 409 Conflict
    }
}
