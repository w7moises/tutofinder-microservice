package com.tutofinder.payment.controllers;

import java.util.List;

import javax.validation.Valid;

import com.tutofinder.payment.dto.PaymentDto;
import com.tutofinder.payment.dto.create.CreatePaymentDto;
import com.tutofinder.payment.entities.Payment;
import com.tutofinder.payment.services.PaymentService;
import com.tutofinder.payment.util.EntityConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin({"http://localhost:4200"})
@Api
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve all existed payments", notes = "This Operation returns all stored payments.")
    @GetMapping(value = "payment")
    public ResponseEntity<List<PaymentDto>> findAll() {
        List<Payment> payments = paymentService.getPayments();
        return new ResponseEntity<>(converter.convertPaymentToDto(payments), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a payment based on Id ", notes = "This Operation returns a payment by Payment Id")
    @GetMapping(value = "payment/{paymentId}")
    public ResponseEntity<PaymentDto> findById(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return new ResponseEntity<>(converter.convertPaymentToDto(payment), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a  payment", notes = "This Operation creates a new payment.")
    @PostMapping(value = "payment")
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody CreatePaymentDto paymentDto) {
        Payment payment = paymentService.createPayment(paymentDto);
        return new ResponseEntity<>(converter.convertPaymentToDto(payment), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes a Payment", notes = "This Operation deletes a payment.")
    @DeleteMapping(value = "payment/{paymentId}")
    public String deletePayment(@PathVariable Long paymentId) {
        String response = paymentService.deletePayment(paymentId);
        return response;
    }
    // TODO: El resto de oper. crud
}

