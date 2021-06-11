package com.tutofinder.customer.dto.create;

import com.tutofinder.customer.dto.AddressDto;
import lombok.Data;

@Data
public class CreateTeacherDto {
    private String firstName;
    private String lastName;
    private String dni;
    private String depositNumber;
    private Double hourlyCost;
    private byte[] profilePicture;
    private AddressDto address;
}
