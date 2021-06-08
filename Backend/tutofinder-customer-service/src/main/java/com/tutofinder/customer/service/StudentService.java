package com.tutofinder.customer.service;

import com.tutofinder.customer.entities.Student;

import java.util.List;

public interface StudentService {
    Student getStudentById(Long studentId);
    List<Student> getStudents();
    Student createStudent(Student createStudent);
    Student updateStudent(Student updateStudent, Long studentId);
    String deleteStudent(Long studentId);
}
