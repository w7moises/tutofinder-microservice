package com.tutofinder.payment.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PaymentDto {
    private Long id;
    private String paymentDescription;
    private Long fatherId;
    private Long cardId;
    private double paymentCost;
    private ReservationDto reservation;
}
