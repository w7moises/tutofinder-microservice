package com.tutofinder.tutorship.repositories;

import com.tutofinder.tutorship.entities.TutorShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorShipRepository extends JpaRepository<TutorShip, Long> {

    // @Query(value = "SELECT t FROM TutorShip ts INNER JOIN Teacher t ON ts.teacherId=t.id ORDER BY CASE WHEN t.membership = true THEN 1 ELSE 2 END , t.membership")
    // List<TutorShip> findAll();
}
