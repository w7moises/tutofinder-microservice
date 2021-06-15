package com.tutofinder.payment.dto;

import lombok.Data;

import java.util.List;

@Data
public class FatherDto {
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private List<StudentDto> students;
}
