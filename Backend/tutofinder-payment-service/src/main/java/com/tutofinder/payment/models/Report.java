package com.tutofinder.payment.models;

import lombok.Data;

@Data
public class Report {
    private Long id;
    private String reportDescription;
    private Tutorship tutorship;
    private Student student;
}
