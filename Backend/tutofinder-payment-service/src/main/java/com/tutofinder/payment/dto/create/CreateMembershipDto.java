package com.tutofinder.payment.dto.create;

import lombok.Data;

@Data
public class CreateMembershipDto {
    private Long teacherId;
    private Long cardId;
    private String expirationDate;
    private String description;
    private double cost;
}