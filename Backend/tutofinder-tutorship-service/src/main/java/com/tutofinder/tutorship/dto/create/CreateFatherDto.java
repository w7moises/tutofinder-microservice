package com.tutofinder.tutorship.dto.create;

import com.tutofinder.tutorship.dto.AddressDto;

import lombok.Data;

@Data
public class CreateFatherDto {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private AddressDto address;
}
