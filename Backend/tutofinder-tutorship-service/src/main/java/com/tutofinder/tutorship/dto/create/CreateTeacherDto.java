package com.tutofinder.tutorship.dto.create;

import com.tutofinder.tutorship.dto.AddressDto;

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
