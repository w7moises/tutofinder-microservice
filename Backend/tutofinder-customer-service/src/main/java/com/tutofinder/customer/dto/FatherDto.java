package com.tutofinder.customer.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class FatherDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private AddressDto address;
    private List<StudentDto> students;
    private List<TeacherDto> teachers;
    private Date createdDate;
    private Date lastUpdateDate;
}
