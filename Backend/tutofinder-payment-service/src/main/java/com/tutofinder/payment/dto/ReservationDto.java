package com.tutofinder.payment.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class ReservationDto {
    private Long id;
    private StudentDto student;
    private TutorshipDto tutorship;
    private Date createAt;
    @PrePersist
    public void PrePersist() {
        this.createAt = new Date();
    }
}
