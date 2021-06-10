package com.tutofinder.tutorship.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CourseInternalServerException extends RuntimeException {
    public CourseInternalServerException(String message) {
        super(message);
    }
}