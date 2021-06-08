package com.tutofinder.customer.service;

import com.tutofinder.customer.entities.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    Student getStudentById(Long studentId);
    List<Student> getStudents();
    Student createStudent(Student createStudent, MultipartFile file) throws IOException;
    Student updateStudent(Student updateStudent, Long studentId, MultipartFile file) throws IOException;
    String deleteStudent(Long studentId);
}
