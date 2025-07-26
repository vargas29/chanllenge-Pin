package com.co.Challenge.domain.exceptions;

public class ClienteInvalidoException extends RuntimeException {
    public ClienteInvalidoException(String message) {
        super(message);
    }
}