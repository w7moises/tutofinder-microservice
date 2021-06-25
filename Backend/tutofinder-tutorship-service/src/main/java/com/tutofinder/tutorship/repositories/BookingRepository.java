package com.tutofinder.tutorship.repositories;

import com.tutofinder.tutorship.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findById(Long id);

}
