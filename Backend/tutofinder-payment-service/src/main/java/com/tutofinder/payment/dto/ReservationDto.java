package com.tutofinder.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservationDto {
    private Long id;
    private Long studentId;
    private Long tutorshipId;
    private Date createAt;
}
