package com.tutofinder.customer.service.impl;

import com.tutofinder.customer.entities.Student;
import com.tutofinder.customer.exceptions.StudentNotFoundException;
import com.tutofinder.customer.repositories.StudentRepository;
import com.tutofinder.customer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.orElseThrow(()-> new StudentNotFoundException(studentId.toString()));
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student createStudent, MultipartFile file) throws IOException {
        Student newStudent = Student.builder()
                .firstName(createStudent.getFirstName())
                .lastName(createStudent.getLastName())
                .dni(createStudent.getDni())
                .educationLevel(createStudent.getEducationLevel())
                .email(createStudent.getEmail())
                .father(createStudent.getFather())
                .profilePicture(file.getBytes())
                .build();
        return studentRepository.save(newStudent);
    }

    @Override
    public Student updateStudent(Student updateStudent, Long studentId, MultipartFile file) throws IOException {
        Optional<Student> student = studentRepository.findById(studentId);
        if(!student.isPresent()){
            throw new StudentNotFoundException(studentId.toString());
        }
        Student newStudent = student.get();
        newStudent.setFirstName(updateStudent.getFirstName());
        newStudent.setLastName(updateStudent.getLastName());
        newStudent.setDni(updateStudent.getDni());
        newStudent.setEmail(updateStudent.getEmail());
        newStudent.setEducationLevel(updateStudent.getEducationLevel());
        newStudent.setFather(updateStudent.getFather());
        newStudent.setProfilePicture(file.getBytes());
        return studentRepository.save(newStudent);
    }

    @Override
    public String deleteStudent(Long studentId) {
        if(!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException(studentId.toString());
        }
        studentRepository.deleteById(studentId);
        return "Student id deleted: "+ studentId;
    }
}

