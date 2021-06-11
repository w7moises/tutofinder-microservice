package com.tutofinder.payment.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class Reservation {
    private Long id;
    private Student student;
    private Tutorship tutorship;
    private Date createAt;
    @PrePersist
    public void PrePersist() {
        this.createAt = new Date();
    }
}
