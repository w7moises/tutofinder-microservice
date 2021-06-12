package com.tutofinder.payment.dto;

import lombok.Data;
import java.util.List;

@Data
public class TutorshipDto {

    private Long id;
    private int cantidadHoras;
    private String descripcionTutoria;
    private CourseDto course;
    private List<ReservationDto> reservations;
    private List<ReportDto> reports;
    private TeacherDto teacher;
}
