package com.treino.libreria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicatedResouceException extends RuntimeException {

    public DuplicatedResouceException(String message) {
        super(message);
    }

    public DuplicatedResouceException(String message, Throwable cause) {
        super(message, cause);
    }

}

// Classe nunca utilizada
