package com.tutofinder.tutorship.dto;

import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.entities.Report;

import lombok.Data;

import java.util.List;

@Data
public class TutorShipDto {
    private Long id;
    private int minutes;
    private String description;
    private Long courseId;
    private List<BookingDto> bookings;
    private List<ReportDto> reports;
}
