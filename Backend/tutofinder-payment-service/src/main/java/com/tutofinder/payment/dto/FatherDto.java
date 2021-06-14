package com.tutofinder.payment.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FatherDto {

    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private byte[] picture;
    private Date createAt;
    private List<StudentDto> students;

    public void PrePersist() {
        this.createAt = new Date();
    }

    public Integer getFotoHashCode(){
        return (this.picture != null) ? this.picture.hashCode() : null;
    }

}
