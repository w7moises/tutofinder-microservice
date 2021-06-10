package com.tutofinder.tutorship.dtos.create;

import com.tutofinder.tutorship.dtos.AddressDto;
import lombok.Data;

@Data
public class CreateFatherDto {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private AddressDto address;
}
