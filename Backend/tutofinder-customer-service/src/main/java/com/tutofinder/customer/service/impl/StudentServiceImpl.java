package com.tutofinder.customer.service.impl;

import com.tutofinder.customer.entities.Student;
import com.tutofinder.customer.exceptions.StudentNotFoundException;
import com.tutofinder.customer.repositories.StudentRepository;
import com.tutofinder.customer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.orElseThrow(()-> new StudentNotFoundException(studentId.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    @Override
    @Transactional
    public Student createStudent(Student createStudent){
        Student newStudent = Student.builder()
                .firstName(createStudent.getFirstName())
                .lastName(createStudent.getLastName())
                .dni(createStudent.getDni())
                .educationLevel(createStudent.getEducationLevel())
                .email(createStudent.getEmail())
                .father(createStudent.getFather())
                .build();
        return studentRepository.save(newStudent);
    }

    @Override
    @Transactional
    public Student updateStudent(Student updateStudent, Long studentId){
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

