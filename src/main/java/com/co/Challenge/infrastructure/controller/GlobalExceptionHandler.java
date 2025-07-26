package com.co.Challenge.infrastructure.controller;

import com.co.Challenge.domain.exceptions.ClienteNotFoundException;
import com.co.Challenge.domain.exceptions.ClienteInvalidoException;
import com.co.Challenge.domain.exceptions.SinClientesRegistradosException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<String> handleClienteNotFound(ClienteNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ClienteInvalidoException.class)
    public ResponseEntity<String> handleClienteInvalido(ClienteInvalidoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(SinClientesRegistradosException.class)
    public ResponseEntity<String> handleSinClientes(SinClientesRegistradosException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(500).body("Error interno del servidor: " + ex.getMessage());
    }
}