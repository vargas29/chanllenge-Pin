package com.co.Challenge.domain.exceptions;

/**
 * Excepción de dominio cuando un cliente no es encontrado.
 */
public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String message) {
        super(message);
    }
}