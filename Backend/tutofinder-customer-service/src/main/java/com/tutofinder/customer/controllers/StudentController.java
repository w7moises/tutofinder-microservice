package com.tutofinder.customer.controllers;

import com.tutofinder.customer.dto.StudentDto;
import com.tutofinder.customer.dto.create.CreateStudentDto;
import com.tutofinder.customer.entities.Student;
import com.tutofinder.customer.service.StudentService;
import com.tutofinder.customer.util.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@Api
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve all existed students", notes = "This Operation returns all stored  students.")
    @GetMapping(value = "student")
    public ResponseEntity<List<StudentDto>> findAll() {
        List<Student> students = studentService.getStudents();
        return new ResponseEntity<>(converter.convertStudentToDto(students), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a student based on Id ", notes = "This Operation returns a student by Father Id")
    @GetMapping(value = "student/{studentId}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        return new ResponseEntity<>(converter.convertStudentToDto(student), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a student", notes = "This Operation creates a new student.")
    @PostMapping(value = "student")
    public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid CreateStudentDto studentDto){
        Student student = converter.convertCreateStudentToEntity(studentDto);
        student = studentService.createStudent(student);
        return new ResponseEntity<>(converter.convertStudentToDto(student), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Modifies a  student", notes = "This Operation modifies a student.")
    @PutMapping(value = "student/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody @Valid CreateStudentDto studentDto,@PathVariable Long studentId){
        Student student = converter.convertCreateStudentToEntity(studentDto);
        student = studentService.updateStudent(student,studentId);
        return new ResponseEntity<>(converter.convertStudentToDto(student), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes a student", notes = "This Operation deletes a student.")
    @DeleteMapping(value = "student/{studentId}")
    public String deleteStudent(@PathVariable Long studentId) {
        String response = studentService.deleteStudent(studentId);
        return response;
    }

}
