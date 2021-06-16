package com.tutofinder.tutorship.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TutorShipNotFoundException extends RuntimeException {
    public TutorShipNotFoundException(String message) {
        super(message);
    }
}