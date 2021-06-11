package com.tutofinder.payment.models;

import lombok.Data;

import java.util.List;

@Data

public class Tutorship {

    private Long id;
    private int cantidadHoras;
    private String descripcionTutoria;
    private Course course;
    private List<Reservation> reservations;
    private List<Report> reports;
    private Teacher teacher;
}
