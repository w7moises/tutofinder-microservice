package com.tutofinder.tutorship.repositories;

import com.tutofinder.tutorship.entities.TutorShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorshipRepository extends JpaRepository<TutorShip,Long> {

    @Query(value = "SELECT t FROM Tutorship t ORDER BY CASE WHEN t.teacher.membresia = true THEN 1 ELSE 2 END , t.docente.membresia")
    List<TutorShip> findAll();
}
