package com.tutofinder.payment.services;

import java.util.List;

import com.tutofinder.payment.dto.create.CreatePaymentDto;
import com.tutofinder.payment.entities.Payment;

public interface PaymentService {
    Payment getPaymentById(Long paymentId) throws RuntimeException;
    List<Payment> getPayments();
    Payment createPayment(CreatePaymentDto createPaymentDto) throws RuntimeException;
    Payment updatePayment(CreatePaymentDto createPaymentDto,Long paymentId) throws RuntimeException;
    String deletePayment(Long paymentId) throws RuntimeException;
}
