package com.tutofinder.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
    private Long id;
    private String cardNumber;
    private String expireDate;
    private String ownerNumber;
}
