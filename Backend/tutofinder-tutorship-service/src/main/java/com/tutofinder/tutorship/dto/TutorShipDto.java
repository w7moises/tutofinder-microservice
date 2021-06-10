package com.tutofinder.tutorship.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.entities.Report;

import javax.persistence.*;
import javax.validation.constraints.Positive;

public class TutorShipDto {
    private Long id;

    private int cantidadMinutos;

    private String descripcionTutoria;

    private Course course;

    private Report report;

    private String status;

}
