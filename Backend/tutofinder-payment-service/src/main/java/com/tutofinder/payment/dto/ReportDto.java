package com.tutofinder.payment.dto;

import lombok.Data;

@Data
public class ReportDto {
    private Long id;
    private String reportDescription;
    private TutorshipDto tutorship;
    private StudentDto student;
}
