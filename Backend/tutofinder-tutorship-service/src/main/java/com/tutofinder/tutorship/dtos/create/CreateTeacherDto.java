package com.tutofinder.tutorship.dtos.create;

import com.tutofinder.tutorship.dtos.AddressDto;

import lombok.Data;

@Data
public class CreateTeacherDto {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String depositNumber;
    private Double hourlyCost;
    private byte[] profilePicture;
    private AddressDto address;
}
