package com.tutofinder.tutorship.dto.create;

import com.tutofinder.tutorship.dto.StudentDto;
import com.tutofinder.tutorship.dto.TutorShipDto;
import lombok.Data;

@Data
public class CreateReportDto {
    private String descriptionReport;
    private StudentDto student;
    private TutorShipDto tutorShip;
}
