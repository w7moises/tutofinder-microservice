package com.tutofinder.payment.dto;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;


@Data
public class StudentDto {

    private Long id;
    private String name;
    private String lastName;
    private String grade;
    private FatherDto father;
    private String dni;
    private String email;
    private byte[] picture;
    private Date createAt;

    @PrePersist
    public void PrePersist() {
        this.createAt = new Date();
    }

    public Integer getFotoHashCode(){
        return (this.picture != null) ? this.picture.hashCode() : null;
    }
}
