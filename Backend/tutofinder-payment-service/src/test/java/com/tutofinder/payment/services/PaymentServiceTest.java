package com.tutofinder.payment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.tutofinder.payment.client.CustomerServiceClient;
import com.tutofinder.payment.client.TutorshipServiceClient;
import com.tutofinder.payment.dto.BookingDto;
import com.tutofinder.payment.dto.FatherDto;
import com.tutofinder.payment.dto.create.CreatePaymentDto;
import com.tutofinder.payment.entities.Card;
import com.tutofinder.payment.entities.Payment;
import com.tutofinder.payment.repositories.CardRepository;
import com.tutofinder.payment.repositories.PaymentRepository;
import com.tutofinder.payment.services.impl.PaymentServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PaymentServiceTest {
    private static Long PAYMENT_ID = 1L;
    private static Long FATHER_ID = 1L;
    private static Long BOOKING_ID = 1L;
    private static Long CARD_ID = 1L;
    private static String DESCRIPTION_PAYMENT = "descripcion";
    private static double PAYMENT_COST = 6.9d;
    private static final String PAYMENT_DELETED = "Payment deleted";

    private static CreatePaymentDto CREATE_PAYMENT_DTO = new CreatePaymentDto();
    private static Payment PAYMENT = new Payment();
    private static FatherDto FATHER = new FatherDto();
    private static BookingDto BOOKING = new BookingDto();
    private static Card CARD = new Card();

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    CardRepository cardRepository;

    @Mock
    CustomerServiceClient customerServiceClient;

    @Mock
    TutorshipServiceClient tutorshipServiceClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        CREATE_PAYMENT_DTO.setPaymentDescription(DESCRIPTION_PAYMENT);
        CREATE_PAYMENT_DTO.setPaymentCost(PAYMENT_COST);
        CREATE_PAYMENT_DTO.setFatherId(FATHER_ID);
        CREATE_PAYMENT_DTO.setCardId(CARD_ID);
        CREATE_PAYMENT_DTO.setBookingId(BOOKING_ID);

        PAYMENT.setId(PAYMENT_ID);
        PAYMENT.setPaymentDescription(DESCRIPTION_PAYMENT);
        PAYMENT.setPaymentCost(PAYMENT_COST);
        PAYMENT.setFatherId(FATHER_ID);
        PAYMENT.setBookingId(BOOKING_ID);
        PAYMENT.setCard(CARD);
    }

    @Test
    public void getPaymentByIdTest() throws RuntimeException {
        Mockito.when(paymentRepository.findById(PAYMENT_ID)).thenReturn(Optional.of(PAYMENT));
        paymentService.getPaymentById(PAYMENT_ID);
    }
    @Test
    public void getPaymentsTest() {
        Mockito.when(paymentRepository.findAll()).thenReturn(Arrays.asList(PAYMENT));
        final List<Payment> response = paymentService.getPayments();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }
    @Test
    public void createPaymentTest() throws RuntimeException {
        Mockito.when(cardRepository.findById(CARD_ID)).thenReturn(Optional.of(CARD));
        Mockito.when(customerServiceClient.findFatherById(FATHER_ID)).thenReturn(Optional.of(FATHER));
        Mockito.when(tutorshipServiceClient.findBookingById(BOOKING_ID)).thenReturn(Optional.of(BOOKING));
        Mockito.when(paymentRepository.findById(PAYMENT_ID)).thenReturn(Optional.of(PAYMENT));
        Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(PAYMENT);
        paymentService.createPayment(CREATE_PAYMENT_DTO);
    }

    @Test
    public void updatePaymentTest() throws RuntimeException {
        Mockito.when(cardRepository.findById(CARD_ID)).thenReturn(Optional.of(CARD));
        Mockito.when(customerServiceClient.findFatherById(FATHER_ID)).thenReturn(Optional.of(FATHER));
        Mockito.when(tutorshipServiceClient.findBookingById(BOOKING_ID)).thenReturn(Optional.of(BOOKING));
        Mockito.when(paymentRepository.findById(PAYMENT_ID)).thenReturn(Optional.of(PAYMENT));
        Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(PAYMENT);
        paymentService.updatePayment(CREATE_PAYMENT_DTO, PAYMENT_ID);
    }

    @Test
    public void deletePaymentOk() throws RuntimeException {
        Mockito.when(paymentRepository.existsById(PAYMENT_ID)).thenReturn(true);
        final String response = paymentService.deletePayment(PAYMENT_ID);
        assertEquals(response, PAYMENT_DELETED);
    }


}
