package com.tutofinder.tutorship.dto;

import lombok.Data;

@Data
public class BookingDto {
    private Long id;
    private Long studentId;
    private Long tutorshipId;
}
