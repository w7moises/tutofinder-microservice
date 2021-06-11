package com.tutofinder.customer.util;

import com.tutofinder.customer.entities.Address;
import com.tutofinder.customer.entities.Father;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtils {
    public static List<Father> getMockFathers(){
        byte[] data = "foto".getBytes();
        Address address01=Address.builder()
                .id(1L)
                .street("Escardo")
                .city("San Miguel")
                .state("Lima")
                .country("Peru")
                .zipCode("12345")
                .build();
        Address address02=Address.builder()
                .id(2L)
                .street("Escardo")
                .city("San Miguel")
                .state("Lima")
                .country("Peru")
                .zipCode("12345")
                .build();
        List<Father> fathers = new ArrayList<>();

        Father father01 = Father.builder()
                .id(1L)
                .firstName("Walter")
                .lastName("Molina")
                .dni("78494676")
                .email("w7moises@gmail.com")
                .address(address01)
                .profilePicture(data)
                .build();
        Father father02 = Father.builder()
                .id(2L)
                .firstName("Walter")
                .lastName("Molina")
                .dni("78494645")
                .email("henry@gmail.com")
                .address(address02)
                .profilePicture(data)
                .build();
        fathers.add(father01);
        fathers.add(father02);

        return fathers;
    }

    public static Father getMockFather(){
        byte[] data = "foto".getBytes();
        Address address=Address.builder()
                .id(1L)
                .street("Escardo")
                .city("San Miguel")
                .state("Lima")
                .country("Peru")
                .zipCode("12345")
                .build();
        return Father.builder()
                .id(1L)
                .firstName("Walter")
                .lastName("Molina")
                .dni("78494676")
                .email("w7moises@gmail.com")
                .address(address)
                .profilePicture(data)
                .build();
    }
}
