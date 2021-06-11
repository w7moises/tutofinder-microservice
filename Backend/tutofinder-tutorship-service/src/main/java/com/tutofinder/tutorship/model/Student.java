package com.tutofinder.tutorship.model;

import com.tutofinder.tutorship.util.EducationLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private Long id;

    private String firstName;

    private String lastName;

    private String dni;

    private EducationLevel educationLevel;


    private String email;

}
