package com.tutofinder.payment.services.impl;

import java.util.List;
import java.util.Optional;

import com.tutofinder.payment.client.CustomerServiceClient;
import com.tutofinder.payment.dto.create.CreatePaymentDto;
import com.tutofinder.payment.entities.Payment;
import com.tutofinder.payment.exceptions.PaymentNotFoundException;
import com.tutofinder.payment.repositories.CardRepository;
import com.tutofinder.payment.repositories.PaymentRepository;
import com.tutofinder.payment.services.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PaymentServiceImpl implements PaymentService{

    @Autowired
        PaymentRepository paymentRepository;

        @Autowired
        CardRepository cardRepository;

        @Autowired
        CustomerServiceClient customerServiceClient;

    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentById(Long paymentId) throws RuntimeException {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        return payment.orElseThrow(()-> new PaymentNotFoundException(paymentId.toString()));
    }

    @Override
    public List<Payment> getPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments;
    }

    @Override
    public Payment createPayment(CreatePaymentDto createPaymentDto) throws RuntimeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Payment updatePayment(CreatePaymentDto createPaymentDto, Long paymentId) throws RuntimeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deletePayment(Long paymentId) throws RuntimeException {
        if (!paymentRepository.existsById(paymentId)) {
            throw new PaymentNotFoundException("PAYMENT_NOT_FOUND");
        }
        paymentRepository.deleteById(paymentId);
        return "Payment deleted";
    }

}
