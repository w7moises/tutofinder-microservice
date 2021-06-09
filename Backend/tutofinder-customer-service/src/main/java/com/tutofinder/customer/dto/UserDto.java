package com.tutofinder.customer.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private boolean enabled;
}
