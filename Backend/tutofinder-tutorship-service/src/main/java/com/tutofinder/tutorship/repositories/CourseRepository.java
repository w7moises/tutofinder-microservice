package com.tutofinder.tutorship.repositories;

import java.util.Optional;

import com.tutofinder.tutorship.entities.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findById(Long id);

    @Query("select c from Course c where upper(c.name) like upper(concat('%',?1,'%'))")
    Optional<Course> findByName(String name);
}
