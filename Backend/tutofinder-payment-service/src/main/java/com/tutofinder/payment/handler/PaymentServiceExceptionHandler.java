package com.tutofinder.payment.handler;

import com.tutofinder.payment.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class PaymentServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalError(Exception exception, WebRequest request) {
        PaymentServiceExceptionResponse response = new PaymentServiceExceptionResponse(exception.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleIncorrectRequest(InvalidRequestException exception, WebRequest request) {
        PaymentServiceExceptionResponse response = new PaymentServiceExceptionResponse(exception.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(MembershipNotFoundException.class)
    public ResponseEntity<Object> handleMembershipNotFound(MembershipNotFoundException exception, WebRequest request) {
        PaymentServiceExceptionResponse response = new PaymentServiceExceptionResponse(exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException exception, WebRequest request) {
        PaymentServiceExceptionResponse response = new PaymentServiceExceptionResponse(exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }
    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<Object> handleCardNotFound(CardNotFoundException exception, WebRequest request) {
        PaymentServiceExceptionResponse response = new PaymentServiceExceptionResponse(exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    //TODO: agregar el resto de handlers
}