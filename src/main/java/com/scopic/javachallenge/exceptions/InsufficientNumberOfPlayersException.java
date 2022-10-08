package com.scopic.javachallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientNumberOfPlayersException extends RuntimeException {
    public InsufficientNumberOfPlayersException(String message) {
        super(message);
    }
}
