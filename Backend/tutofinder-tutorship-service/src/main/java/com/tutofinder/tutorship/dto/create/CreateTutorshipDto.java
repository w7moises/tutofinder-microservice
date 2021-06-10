package com.tutofinder.tutorship.dto.create;

import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.entities.Report;
import lombok.Data;

@Data
public class CreateTutorshipDto {
    private int cantidadMinutos;

    private String descripcionTutoria;

    private Course course;

    private Report report;

    private String status;

}
