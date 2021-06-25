package com.tutofinder.tutorship.handler;

import java.time.LocalDateTime;

import com.tutofinder.tutorship.exceptions.BookingInternalServerException;
import com.tutofinder.tutorship.exceptions.BookingNotFoundException;
import com.tutofinder.tutorship.exceptions.CourseInternalServerException;
import com.tutofinder.tutorship.exceptions.CourseNotFoundException;
import com.tutofinder.tutorship.exceptions.ReportInternalServerException;
import com.tutofinder.tutorship.exceptions.ReportNotFoundException;
import com.tutofinder.tutorship.exceptions.StudentNotFoundException;
import com.tutofinder.tutorship.exceptions.TutorShipInternalServerException;
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

    @ExceptionHandler(BookingInternalServerException.class)
    public ResponseEntity<Object> handleIncorrectRequest(BookingInternalServerException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(CourseInternalServerException.class)
    public ResponseEntity<Object> handleIncorrectRequest(CourseInternalServerException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ReportInternalServerException.class)
    public ResponseEntity<Object> handleIncorrectRequest(ReportInternalServerException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(TutorShipInternalServerException.class)
    public ResponseEntity<Object> handleIncorrectRequest(TutorShipInternalServerException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<Object> handleCourseResourceNotFound(BookingNotFoundException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> handleCourseResourceNotFound(CourseNotFoundException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(TutorShipNotFoundException.class)
    public ResponseEntity<Object> handleTutorShipResourceNotFound(TutorShipNotFoundException exception,
            WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<Object> handleReportResourceNotFound(ReportNotFoundException exception, WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleStudentResourceNotFound(StudentNotFoundException exception,
            WebRequest request) {
        TutorShipServiceExceptionResponse response = new TutorShipServiceExceptionResponse(exception.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

}