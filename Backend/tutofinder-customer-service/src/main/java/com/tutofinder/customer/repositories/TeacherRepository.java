package com.tutofinder.customer.repositories;

import com.tutofinder.customer.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
