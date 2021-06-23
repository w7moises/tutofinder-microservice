package com.tutofinder.payment.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MembershipDto {
    private Long id;
    private Date expirationDate;
    private String description;
    private double cost;
    private Long teacherId;
    private Long cardId;
}
