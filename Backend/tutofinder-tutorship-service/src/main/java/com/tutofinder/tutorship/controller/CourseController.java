package com.tutofinder.tutorship.controller;

import com.tutofinder.tutorship.dto.CourseDto;
import com.tutofinder.tutorship.dto.create.CreateCourseDto;
import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.service.CourseService;
import com.tutofinder.tutorship.util.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@Api
@RestController
public class CourseController {
    @Autowired
    private CourseService courseservice;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve all existed courses", notes = "This Operation returns all stored courses.")
    @GetMapping(value = "course")
    public ResponseEntity<List<CourseDto>> findAll() {
        List<Course> courses = courseservice.getCourses();
        return new ResponseEntity<>(converter.convertCourseToDto(courses), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a father based on Id ", notes = "This Operation returns a father by course Id")
    @GetMapping(value = "course/{courseId}")
    public ResponseEntity<CourseDto> findById(@PathVariable Long courseId) {
        Course course = courseservice.getCourseById(courseId);
        return new ResponseEntity<>(converter.convertCourseToDto(course), HttpStatus.OK);
    }
    @ApiOperation(value = "Retrieve a father by name", notes = "This Operation return a c by name.")
    @GetMapping(value = "father/email/{email}")
    public ResponseEntity<CourseDto> findByName(@PathVariable String name) {
        Course father = courseservice.getCourseByName(name);
        return new ResponseEntity<>(converter.convertCourseToDto(father), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a  course", notes = "This Operation creates a new course.")
    @PostMapping(value = "course")
    public ResponseEntity<CourseDto> createCourse(@RequestBody @Valid CreateCourseDto courseDto){
       // Course course = converter.convertCreateCourseToEntity(courseDto);
        Course course = courseservice.createCourse(courseDto);
        return new ResponseEntity<>(converter.convertCourseToDto(course), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Updates a  course", notes = "This Operation updates a new course.")
    @PutMapping(value = "course/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody  @Valid CreateCourseDto courseDto,@PathVariable Long courseId){
        // Course course = converter.convertCreateCourseToEntity(courseDto);
        Course course = courseservice.updateCourse(courseDto,courseId);
        return new ResponseEntity<>(converter.convertCourseToDto(course), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes a Course", notes = "This Operation deletes a course.")
    @DeleteMapping(value = "course/{courseId}")
    public String deleteCourse(@PathVariable Long courseId) {
        String response = courseservice.deleteCourse(courseId);
        return response;
    }


}
