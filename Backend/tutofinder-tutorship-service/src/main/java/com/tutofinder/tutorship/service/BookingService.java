package com.tutofinder.tutorship.service;

import java.util.List;

import com.tutofinder.tutorship.dto.create.CreateBookingDto;
import com.tutofinder.tutorship.entities.Booking;

public interface BookingService {
    Booking getBookingById(Long bookingId);

    List<Booking> getBookings();

    Booking createBooking(CreateBookingDto createBookingDto);

    String deleteBooking(Long bookingId);
}
