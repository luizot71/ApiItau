package com.itau.br.app.handler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends ClientException {

    private static final long serialVersionUID = 1L;

    public ClientNotFoundException(String message) {
        super(message, "", "");
    }

    public ClientNotFoundException(String message, Object parameter, String field) {
        super(message, parameter, field);
    }

}