package com.tutofinder.tutorship.dto.create;

import com.tutofinder.tutorship.dto.TeacherDto;
import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.entities.Report;

public class CreateTutorShipDto {
    private int minutesAmmount;

    private String tutorShipDescription;

    private Course course;

    private Report report;

    private String status;

    private TeacherDto teacher;
}