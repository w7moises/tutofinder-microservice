package com.tutofinder.tutorship.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tutofinder.tutorship.dto.CourseDto;
import com.tutofinder.tutorship.dto.create.CreateCourseDto;
import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.exceptions.CourseInternalServerException;
import com.tutofinder.tutorship.exceptions.CourseNotFoundException;
import com.tutofinder.tutorship.repositories.CourseRepository;
import com.tutofinder.tutorship.service.CourseService;
import com.tutofinder.tutorship.util.ExceptionMessagesEnum;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Course getCourseById(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.orElseThrow(()-> new CourseNotFoundException(courseId.toString()));
    }

    @Override
    public Course getCourseByName(String name) {
        Optional<Course>  course = courseRepository.findByName(name);
        return course.orElseThrow(() -> new CourseNotFoundException(ExceptionMessagesEnum.COURSE_NOT_FOUND.getValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public Course createCourse(CreateCourseDto createCourseDto) {
        Course newcourse = Course.builder()
                .name(createCourseDto.getName())
                .build();
        return courseRepository.save(newcourse);

    }

    @Override
    public Course updateCourse(CreateCourseDto createCourseDto, Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (!course.isPresent()) {
            throw new CourseNotFoundException(ExceptionMessagesEnum.COURSE_NOT_FOUND.getValue());
        }
        Course courseEntity = course.get();
        Long id;
        courseEntity.setName(createCourseDto.getName());
        try {
            id = courseRepository.save(courseEntity).getId();
        } catch (final Exception e) {
            throw new CourseInternalServerException("couldnt't update course object");
        }
        return getCourseById(id);
    }

    @Override
    public String deleteCourse(Long courseId) {
        courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(ExceptionMessagesEnum.COURSE_NOT_FOUND.getValue()));

        try {
            courseRepository.deleteById(courseId);
        } catch (Exception ex) {
            throw new CourseInternalServerException("couldnt't delete course object");
        }

        return "CURSO_DELETED";
    }

    private Course getCourseEntity(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(ExceptionMessagesEnum.COURSE_NOT_FOUND.getValue()));
    }
}
