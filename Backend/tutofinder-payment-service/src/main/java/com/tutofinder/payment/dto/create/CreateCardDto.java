package com.tutofinder.payment.dto.create;

import lombok.Data;

@Data
public class CreateCardDto {
    private String cardNumber;
    private String expireDate;
    private String ownerName;
}
