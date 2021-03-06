package com.tutofinder.tutorship.dto.create;

import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.entities.Report;

import lombok.Data;

@Data
public class CreateTutorShipDto {
    private int minutesAmmount;
    private String tutorShipDescription;
    private Long courseId;
    private Long teacherId;
}