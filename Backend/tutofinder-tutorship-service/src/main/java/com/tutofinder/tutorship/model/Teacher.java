package com.tutofinder.tutorship.model;

import lombok.Builder;
import lombok.Data;
import org.apache.tomcat.jni.Address;

@Data
@Builder
public class Teacher {
    private Long id;


    private String firstName;


    private String lastName;


    private String dni;


    private Address address;


    private String email;


    private String depositNumber;


    private Double hourlyCost;


    private String membership;

    private byte[] profilePicture;

}
