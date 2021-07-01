package com.tutofinder.tutorship.controller;

import java.util.List;

import javax.validation.Valid;

import com.tutofinder.tutorship.dto.BookingDto;
import com.tutofinder.tutorship.dto.create.CreateBookingDto;
import com.tutofinder.tutorship.entities.Booking;
import com.tutofinder.tutorship.service.BookingService;
import com.tutofinder.tutorship.util.EntityConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin({ "http://localhost:4200" })
@Api
@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve all existed bookings", notes = "This Operation returns all stored bookings.")
    @GetMapping(value = "booking")
    public ResponseEntity<List<BookingDto>> findAll() {
        List<Booking> bookings = bookingService.getBookings();
        return new ResponseEntity<>(converter.convertBookingToDto(bookings), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a father based on Id ", notes = "This Operation returns a father by booking Id")
    @GetMapping(value = "booking/{bookingId}")
    public ResponseEntity<BookingDto> findById(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(converter.convertBookingToDto(booking), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a  booking", notes = "This Operation creates a new booking.")
    @PostMapping(value = "booking")
    public ResponseEntity<BookingDto> createBooking(@RequestBody @Valid CreateBookingDto createBookingDto) {
        Booking booking = bookingService.createBooking(createBookingDto);
        return new ResponseEntity<>(converter.convertBookingToDto(booking), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes a booking", notes = "This Operation deletes a booking.")
    @DeleteMapping(value = "booking/{bookingId}")
    public String deleteBooking(@PathVariable Long bookingId) {
        String response = bookingService.deleteBooking(bookingId);
        return response;
    }
}
