package com.tutofinder.tutorship.service.impl;

import java.util.List;
import java.util.Optional;

import com.tutofinder.tutorship.client.CustomerServiceClient;
import com.tutofinder.tutorship.dto.StudentDto;
import com.tutofinder.tutorship.dto.create.CreateBookingDto;
import com.tutofinder.tutorship.entities.Booking;
import com.tutofinder.tutorship.entities.TutorShip;
import com.tutofinder.tutorship.exceptions.BookingInternalServerException;
import com.tutofinder.tutorship.exceptions.BookingNotFoundException;
import com.tutofinder.tutorship.exceptions.StudentNotFoundException;
import com.tutofinder.tutorship.repositories.BookingRepository;
import com.tutofinder.tutorship.repositories.TutorShipRepository;
import com.tutofinder.tutorship.service.BookingService;
import com.tutofinder.tutorship.util.ExceptionMessagesEnum;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TutorShipRepository tutorShipRepository;

    @Autowired
    CustomerServiceClient customerServiceClient;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Booking getBookingById(Long bookingId) {
        Optional<Booking> course = bookingRepository.findById(bookingId);
        return course.orElseThrow(()-> new BookingNotFoundException(bookingId.toString()));
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking createBooking(CreateBookingDto createBookingDto) {
        final TutorShip tutorShip = tutorShipRepository.findById(createBookingDto.getTutorShipId())
                .orElseThrow(() -> new StudentNotFoundException(ExceptionMessagesEnum.STUDENT_NOT_FOUND.getValue()));
        final StudentDto studentDto = customerServiceClient.findStudentById(createBookingDto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(ExceptionMessagesEnum.STUDENT_NOT_FOUND.getValue()));
        Booking bookingEntity = new Booking();
        Long id;
        bookingEntity.setStudentId(createBookingDto.getStudentId());
        bookingEntity.setTutorShip(tutorShip);
        try {
            id = bookingRepository.save(bookingEntity).getId();
        } catch (final Exception e) {
            throw new BookingInternalServerException("couldnt't create booking object");
        }
        return getBookingById(id);
    }

    @Override
    public String deleteBooking(Long bookingId) {
        bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(ExceptionMessagesEnum.BOOKING_NOT_FOUND.getValue()));
        try {
            bookingRepository.deleteById(bookingId);
        } catch (final Exception e) {
            throw new BookingInternalServerException("couldnt't delete booking object");
        }
        return "BOOKING_DELETED";
    }
}
