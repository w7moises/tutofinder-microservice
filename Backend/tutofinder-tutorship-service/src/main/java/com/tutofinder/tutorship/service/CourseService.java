package com.tutofinder.tutorship.service;

import java.util.List;

import com.tutofinder.tutorship.dto.CourseDto;
import com.tutofinder.tutorship.dto.create.CreateCourseDto;

public interface CourseService {
    CourseDto getCourseById(Long CourseId);

    CourseDto getCourseByName(String name);

    List<CourseDto> getCourses();

    CourseDto createCourse(CreateCourseDto createCourseDto);

    CourseDto updateCourse(CreateCourseDto createCourseDto, Long CourseId);

    String deleteCourse(Long CourseId);
    
    ///commit
}
