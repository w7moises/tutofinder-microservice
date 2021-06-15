package com.tutofinder.customer.controllers;

import com.tutofinder.customer.dto.UserDto;
import com.tutofinder.customer.dto.create.CreateTeacherDto;
import com.tutofinder.customer.entities.Teacher;
import com.tutofinder.customer.entities.User;
import com.tutofinder.customer.repositories.UserRepository;
import com.tutofinder.customer.service.UserService;
import com.tutofinder.customer.util.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@Api
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EntityConverter converter;

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Retrieve all existed users", notes = "This Operation returns all stored users.")
    @GetMapping(value = "user")
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(converter.convertUserToDto(users), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a user", notes = "This Operation creates a new user.")
    @PostMapping(value = "user/{rol}")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user,@PathVariable Long rol){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user = userService.registerUser(user,rol);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
