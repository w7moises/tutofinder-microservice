package com.tutofinder.customer.controllers;


import com.tutofinder.customer.dto.TeacherDto;
import com.tutofinder.customer.dto.create.CreateTeacherDto;
import com.tutofinder.customer.entities.Teacher;
import com.tutofinder.customer.service.TeacherService;
import com.tutofinder.customer.util.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@Api
@RestController
@Secured("ROLE_TEACHER")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve a picture from the teacher", notes = "This Operation returns a teacher's picture.")
    @GetMapping("/teacher/img/{teacherId}")
    public ResponseEntity<?> findPicture(@PathVariable Long teacherId){
        Teacher teacher = teacherService.getTeacherById(teacherId);
        Resource picture = new ByteArrayResource(teacher.getProfilePicture());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(picture);
    }

    @ApiOperation(value = "Retrieve all existed teachers", notes = "This Operation returns all stored teachers.")
    @GetMapping(value = "teacher")
    public ResponseEntity<List<TeacherDto>> findAll() {
        List<Teacher> teachers = teacherService.getTeachers();
        return new ResponseEntity<>(converter.convertTeacherToDto(teachers), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a teacher based on Id ", notes = "This Operation returns a teacher by Teacher Id")
    @GetMapping(value = "teacher/{teacherId}")
    public ResponseEntity<TeacherDto> findById(@PathVariable Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        return new ResponseEntity<>(converter.convertTeacherToDto(teacher), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a teacher", notes = "This Operation creates a new teacher.")
    @PostMapping(value = "teacher/{username}")
    public ResponseEntity<TeacherDto> createTeacher(@Valid CreateTeacherDto teacherDto,@PathVariable String username, @RequestParam MultipartFile file) throws IOException {
        Teacher teacher = converter.convertCreateTeacherToEntity(teacherDto);
        teacher = teacherService.createTeacher(teacher,file,username);
        return new ResponseEntity<>(converter.convertTeacherToDto(teacher), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Modifies a teacher", notes = "This Operation modifies a teacher.")
    @PutMapping(value = "teacher/{teacherId}")
    public ResponseEntity<TeacherDto> updateTeacher(@Valid CreateTeacherDto teacherDto,@PathVariable Long teacherId, @RequestParam MultipartFile file) throws IOException{
        Teacher teacher = converter.convertCreateTeacherToEntity(teacherDto);
        teacher = teacherService.updateTeacher(teacher,teacherId,file);
        return new ResponseEntity<>(converter.convertTeacherToDto(teacher), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes a teacher", notes = "This Operation deletes a teacher.")
    @DeleteMapping(value = "teacher/{teacherId}")
    public String deleteTeacher(@PathVariable Long teacherId) {
        String response = teacherService.deleteTeacher(teacherId);
        return response;
    }
}
