package com.tutofinder.payment.dto;


import lombok.Data;

import java.util.Date;

@Data
public class TeacherDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String depositNumber;
    private Double hourlyCost;
    private String membership;
    private AddressDto address;
    private Date createdDate;
    private Date lastUpdateDate;
}
