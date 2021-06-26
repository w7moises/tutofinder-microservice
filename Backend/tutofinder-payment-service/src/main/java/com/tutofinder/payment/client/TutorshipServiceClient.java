package com.tutofinder.payment.client;

import java.util.Optional;

import com.tutofinder.payment.config.PaymentServiceConfig;
import com.tutofinder.payment.dto.BookingDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TutorshipServiceClient {

    private RestTemplate restTemplate;

    @Autowired
    private PaymentServiceConfig config;

    public TutorshipServiceClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public Optional<BookingDto> findBookingById(Long bookingId) {
        Optional<BookingDto> result = Optional.empty();
        try {
            result = Optional.ofNullable(restTemplate.getForObject(config.getTutorshipServiceUrl() + "/booking/{id}",
            BookingDto.class, bookingId));
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
        }
        return result;
    }
}
