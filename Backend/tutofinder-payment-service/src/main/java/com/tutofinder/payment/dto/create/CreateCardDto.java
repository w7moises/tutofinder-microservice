package com.tutofinder.payment.dto.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCardDto {
    private String cardNumber;
    private String expireDate;
    private String ownerNumber;
}
