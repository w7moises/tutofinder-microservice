package com.tutofinder.customer.dto;

import lombok.Data;

@Data
public class TeacherDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String depositNumber;
    private Double hourlyCost;
    private Boolean membership;
    private byte[] profilePicture;
    private AddressDto address;
}
