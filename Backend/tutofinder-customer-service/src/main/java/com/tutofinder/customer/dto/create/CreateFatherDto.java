package com.tutofinder.customer.dto.create;

import com.tutofinder.customer.dto.AddressDto;
import lombok.Data;

@Data
public class CreateFatherDto {
    private String firstName;
    private String lastName;
    private String dni;
    private byte[] profilePicture;
    private AddressDto address;
}
