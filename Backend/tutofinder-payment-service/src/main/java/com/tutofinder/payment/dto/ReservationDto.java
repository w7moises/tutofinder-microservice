package com.tutofinder.payment.dto;

import lombok.Data;

@Data
public class ReservationDto {
    private Long id;
    private StudentDto student;
    private TutorshipDto tutorship;
}
