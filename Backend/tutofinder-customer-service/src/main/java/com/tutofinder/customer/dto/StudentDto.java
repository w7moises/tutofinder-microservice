package com.tutofinder.customer.dto;

import com.tutofinder.customer.util.EducationLevel;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private EducationLevel educationLevel;
}
