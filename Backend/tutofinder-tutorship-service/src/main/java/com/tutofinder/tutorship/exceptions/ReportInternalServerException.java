package com.tutofinder.tutorship.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ReportInternalServerException extends RuntimeException {
    public ReportInternalServerException(String message) {
        super(message);
    }
}