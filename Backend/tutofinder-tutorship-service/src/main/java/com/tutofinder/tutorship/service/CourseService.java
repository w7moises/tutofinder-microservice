package com.tutofinder.tutorship.service;

import java.util.List;

import com.tutofinder.tutorship.dto.CourseDto;
import com.tutofinder.tutorship.dto.create.CreateCourseDto;
import com.tutofinder.tutorship.entities.Course;

public interface CourseService {
    Course getCourseById(Long CourseId);

    Course getCourseByName(String name);

    List<CourseDto> getCourses();

    Course createCourse(CreateCourseDto createCourseDto);

    Course updateCourse(CreateCourseDto createCourseDto, Long CourseId);

    String deleteCourse(Long CourseId);
}
