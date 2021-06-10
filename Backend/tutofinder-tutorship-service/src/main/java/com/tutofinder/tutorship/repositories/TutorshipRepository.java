package com.tutofinder.tutorship.repositories;

import com.tutofinder.tutorship.entities.Tutorship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorshipRepository extends JpaRepository<Tutorship,Long> {
    Optional<Tutorship> findById(Long id);

    List<Tutorship> findAll();
}
