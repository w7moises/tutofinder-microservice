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
    @Transactional(readOnly = true)
    public CourseDto getCourseById(Long courseId) {
        return modelMapper.map(getCourseEntity(courseId), CourseDto.class);
    }

    @Override
    public CourseDto getCourseByName(String name) {
        final Course course = courseRepository.findByName(name)
                .orElseThrow(() -> new CourseNotFoundException(ExceptionMessagesEnum.COURSE_NOT_FOUND.getValue()));
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDto> getCourses() {
        final List<Course> coursesEntity = courseRepository.findAll();
        return coursesEntity.stream().map(service -> modelMapper.map(service, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto createCourse(CreateCourseDto createCourseDto) {
        Course courseEntity = new Course();
        Long id;
        courseEntity.setName(createCourseDto.getName());
        try {
            id = courseRepository.save(courseEntity).getId();
        } catch (final Exception e) {
            throw new CourseInternalServerException("couldnt't create course object");
        }
        return modelMapper.map(getCourseEntity(id), CourseDto.class);
    }

    @Override
    public CourseDto updateCourse(CreateCourseDto createCourseDto, Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (!course.isPresent()) {
            throw new CourseNotFoundException(ExceptionMessagesEnum.COURSE_NOT_FOUND.getValue()));
        }
        Course courseEntity = course.get();
        Long id;
        courseEntity.setName(createCourseDto.getName());
        try {
            id = courseRepository.save(courseEntity).getId();
        } catch (final Exception e) {
            throw new CourseInternalServerException("couldnt't update course object");
        }
        return modelMapper.map(getCourseEntity(id), CourseDto.class);
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
