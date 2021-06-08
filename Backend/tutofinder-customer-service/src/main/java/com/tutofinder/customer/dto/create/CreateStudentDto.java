package com.tutofinder.customer.dto.create;

import com.tutofinder.customer.dto.FatherDto;
import com.tutofinder.customer.util.EducationLevel;
import lombok.Data;

@Data
public class CreateStudentDto {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private EducationLevel educationLevel;
    private FatherDto father;
}
