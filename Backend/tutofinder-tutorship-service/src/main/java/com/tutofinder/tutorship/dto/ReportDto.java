package com.tutofinder.tutorship.dto;

import lombok.Data;

@Data
public class ReportDto {
    private Long id;
    private String descriptionReport;
    private StudentDto student;
    private  TutorShipDto tutorShip;

}
