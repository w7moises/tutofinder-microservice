package com.tutofinder.tutorship.dto.create;

import com.tutofinder.tutorship.dto.FatherDto;
import com.tutofinder.tutorship.util.EducationLevel;
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
