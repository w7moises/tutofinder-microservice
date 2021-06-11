package com.tutofinder.payment.repositories;

import com.tutofinder.payment.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository  extends JpaRepository<Card,Long> {
    Optional<Card> findById(Long id);
    List<Card> findByOwnerName(String nombre);
}
