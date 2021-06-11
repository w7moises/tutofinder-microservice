package com.tutofinder.tutorship.repositories;

import com.tutofinder.tutorship.entities.TutorShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorShipRepository extends JpaRepository<TutorShip, Long> {

    @Query(value = "SELECT t FROM TutorShip t ORDER BY CASE WHEN t.teacher.membership = true THEN 1 ELSE 2 END , t.teacher.membership")
    List<TutorShip> findAll();
}
