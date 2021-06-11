package com.tutofinder.payment.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Data

public class Student {

    private Long id;
    private String name;
    private String lastName;
    private String grade;
    private Father father;
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
