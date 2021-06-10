package com.tutofinder.tutorship.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private boolean enabled;
}
