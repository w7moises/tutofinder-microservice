package com.tutofinder.tutorship.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import com.tutofinder.tutorship.dtos.TeacherDto;

@Slf4j
@Component
public class TeacherServiceClient {
    private RestTemplate restTemplate;

    @Autowired
    private TeacherServiceClient config;

    public TeacherServiceClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public Optional<TeacherDto> findStudentById(String accountId) {
        Optional<TeacherDto> result = Optional.empty();
        try {
            result = Optional.ofNullable(restTemplate.getForObject(
										config.getCustomerServiceUrl() + "/{id}", 
										TeacherDto.class, accountId));
										//AccountDto.class: lo que quiero recibir
										//accountId: lo que tengo que dar
        }
        catch (HttpClientErrorException ex)   {
            if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
        }
        return result;
    }
}
