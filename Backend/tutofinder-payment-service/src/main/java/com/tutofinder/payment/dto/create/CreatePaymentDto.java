package com.tutofinder.payment.dto.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentDto {
    private String paymentDescription;
    private Long fatherId;
    private Long cardId;
    private double paymentCost;
    private Long bookingId;
}
