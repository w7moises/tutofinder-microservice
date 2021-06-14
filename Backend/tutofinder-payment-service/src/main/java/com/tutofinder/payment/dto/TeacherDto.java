package com.tutofinder.payment.dto;


import lombok.Data;

import javax.persistence.PrePersist;
import java.util.Date;
import java.util.List;

@Data

public class TeacherDto {

    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String address;
    private String email;
    private String accountNumber;
    private double costPerHour;
    private Boolean membership;
    private byte[] picture;
    private List<TutorshipDto> tutorships;
    private Date createAt;

    @PrePersist
    public void PrePersist() {
        this.createAt = new Date();
    }

    public Integer getFotoHashCode(){
        return (this.picture != null) ? this.picture.hashCode() : null;
    }
}
