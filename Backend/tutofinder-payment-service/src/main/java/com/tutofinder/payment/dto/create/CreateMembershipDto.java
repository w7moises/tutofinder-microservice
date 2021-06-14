package com.tutofinder.payment.dto.create;

import java.util.Date;

import lombok.Data;

@Data
public class CreateMembershipDto {
    private Long teacherId;
    private Long cardId;
    private Date expirationDate;
    private String description;
    private double cost;
}