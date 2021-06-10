package com.geekshirt.TutorShipservice.handler;

import java.time.LocalDateTime;

import javax.security.auth.login.AccountNotFoundException;

import com.tutofinder.tutorship.exceptions.CourseNotFoundException;
import com.tutofinder.tutorship.exceptions.ReportNotFoundException;
import com.tutofinder.tutorship.exceptions.StudentNotFoundException;
import com.tutofinder.tutorship.exceptions.TutorShipNotFoundException;
import com.tutofinder.tutorship.exceptions.TutorShipServiceExceptionResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class TutorShipServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(CourseNotFoundException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(TutorShipNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(TutorShipNotFoundException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(StudentNotFoundException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ReportNotFoundException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }
}