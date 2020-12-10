package com.itau.br.app.handler.exceptions;

public class ValueAboveLimitException extends Exception {


    private static final long serialVersionUID = -1L;

    public ValueAboveLimitException() {
    }

    public ValueAboveLimitException(String message) {
        super(message);
    }
}
