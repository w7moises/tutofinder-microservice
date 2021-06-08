package com.tutofinder.customer.repositories;

import com.tutofinder.customer.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentController extends JpaRepository<Student,Long> {
}
