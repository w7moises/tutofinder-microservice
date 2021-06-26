package com.tutofinder.payment.services.impl;

import java.util.List;
import java.util.Optional;

import com.tutofinder.payment.client.CustomerServiceClient;
import com.tutofinder.payment.client.TutorshipServiceClient;
import com.tutofinder.payment.dto.BookingDto;
import com.tutofinder.payment.dto.FatherDto;
import com.tutofinder.payment.dto.create.CreatePaymentDto;
import com.tutofinder.payment.entities.Card;
import com.tutofinder.payment.entities.Payment;
import com.tutofinder.payment.exceptions.CardNotFoundException;
import com.tutofinder.payment.exceptions.InternalServerErrorException;
import com.tutofinder.payment.exceptions.InvalidRequestException;
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

    @Autowired
    TutorshipServiceClient tutorshipServiceClient;

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
        Card card = cardRepository.findById(createPaymentDto.getCardId())
        .orElseThrow(()-> new CardNotFoundException(createPaymentDto.getCardId().toString()));
        FatherDto fatherDto = customerServiceClient.findFatherById(createPaymentDto.getFatherId())
        .orElseThrow(()-> new InvalidRequestException(createPaymentDto.getFatherId().toString()));
        BookingDto bookingDto = tutorshipServiceClient.findBookingById(createPaymentDto.getBookingId())
        .orElseThrow(()-> new InvalidRequestException(createPaymentDto.getBookingId().toString()));
        Payment payment = new Payment();
        payment.setBookingId(bookingDto.getId());
        payment.setFatherId(fatherDto.getId());
        payment.setCard(card);
        payment.setPaymentDescription(createPaymentDto.getPaymentDescription());
        payment.setPaymentCost(createPaymentDto.getPaymentCost());
        Long id;
        try{
            id = paymentRepository.save(payment).getId();
        } catch (Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR");
        }
        return getPaymentById(id);
    }

    @Override
    public Payment updatePayment(CreatePaymentDto createPaymentDto, Long paymentId) throws RuntimeException {
        Card card = cardRepository.findById(createPaymentDto.getCardId())
        .orElseThrow(()-> new CardNotFoundException(createPaymentDto.getCardId().toString()));
        FatherDto fatherDto = customerServiceClient.findFatherById(createPaymentDto.getFatherId())
        .orElseThrow(()-> new InvalidRequestException(createPaymentDto.getFatherId().toString()));
        BookingDto bookingDto = tutorshipServiceClient.findBookingById(createPaymentDto.getBookingId())
        .orElseThrow(()-> new InvalidRequestException(createPaymentDto.getBookingId().toString()));
        Payment payment = paymentRepository.findById(paymentId)
        .orElseThrow(()-> new PaymentNotFoundException(paymentId.toString()));
        payment.setBookingId(bookingDto.getId());
        payment.setFatherId(fatherDto.getId());
        payment.setCard(card);
        payment.setPaymentDescription(createPaymentDto.getPaymentDescription());
        payment.setPaymentCost(createPaymentDto.getPaymentCost());
        try{
            paymentRepository.save(payment);
        } catch (Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR");
        }
        return getPaymentById(paymentId);
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
