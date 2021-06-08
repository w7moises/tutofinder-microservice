package com.tutofinder.customer.dto;

import lombok.Data;

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
}
