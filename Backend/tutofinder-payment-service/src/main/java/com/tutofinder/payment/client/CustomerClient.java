package com.tutofinder.payment.client;

import java.util.Optional;

import com.tutofinder.payment.config.MembershipServiceConfig;
import com.tutofinder.payment.dto.TeacherDto;

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
    private MembershipServiceConfig config;

    public CustomerServiceClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public Optional<TeacherDto> findTeacherById(Long teacherId) {
        Optional<TeacherDto> result = Optional.empty();
        try {
            result = Optional.ofNullable(restTemplate.getForObject(config.getCustomerServiceUrl() + "teacher/{id}",
                    TeacherDto.class, teacherId));
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
        }
        return result;
    }
}
