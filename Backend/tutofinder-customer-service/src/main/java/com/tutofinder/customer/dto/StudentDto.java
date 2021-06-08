package com.tutofinder.customer.dto;

import com.tutofinder.customer.util.EducationLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private EducationLevel educationLevel;
    private Date createdDate;
    private Date lastUpdateDate;
}
