package com.itau.br.app.handler.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    protected final String field;

    @Getter
    protected final Object parameter;

    public ClientException(String message, Object parameter, String field) {
        super(message);
        this.parameter = parameter;
        this.field = field;
    }
}