package com.tutofinder.tutorship.client;

import java.util.Optional;

import com.tutofinder.tutorship.config.TutorShipServiceConfig;
import com.tutofinder.tutorship.dto.StudentDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerServiceClient {
    private RestTemplate restTemplate;

    @Autowired
    private TutorShipServiceConfig config;

    public CustomerServiceClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public Optional<StudentDto> findStudentById(String studentId) {
        Optional<StudentDto> result = Optional.empty();
        try {
            result = Optional.ofNullable(restTemplate.getForObject(
										config.getCustomerServiceUrl() + "student/{id}", 
										StudentDto.class, studentId));
        }
        catch (HttpClientErrorException ex)   {
            if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
        }
        return result;
    }

    


}
