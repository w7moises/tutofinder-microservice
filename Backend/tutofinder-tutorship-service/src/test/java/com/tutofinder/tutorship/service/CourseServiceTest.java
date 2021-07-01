package com.tutofinder.tutorship.service;

import com.tutofinder.tutorship.dto.create.CreateCourseDto;
import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.repositories.CourseRepository;
import com.tutofinder.tutorship.service.impl.CourseServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    private static final Long COURSE_ID = 1L;
    private static final String NAME_COURSE = "COMPLEXITY";
    private static final Long TEACHER_ID = 1L;
    public static final Course COURSE = new Course();

    CreateCourseDto CREATE_COURSE_DTO = new CreateCourseDto();
    private static final String COURSE_DELETED ="CURSO_DELETED";
    private static final Optional<Course> OPTIONAL_EMPTY = Optional.empty();
    private static final Optional<Course> OPTIONAL_COURSE = Optional.of(COURSE);

    @InjectMocks
    private CourseServiceImpl courseServiceImpl;

    @Mock
    private CourseRepository courseRepository;

    @Before
    public void init() throws RuntimeException{
        MockitoAnnotations.initMocks(this);
        COURSE.setId(COURSE_ID);
        COURSE.setName(NAME_COURSE);

        CREATE_COURSE_DTO.setName(NAME_COURSE);

    }

    @Test
    public void getCourseByIdTest() throws RuntimeException {
        Mockito.when(courseRepository.findById(COURSE_ID)).thenReturn(OPTIONAL_COURSE);
        courseServiceImpl.getCourseById(COURSE_ID);
    }

    @Test
    public void getCoursesTest(){
        final Course course = new Course();
        Mockito.when(courseRepository.findAll()).thenReturn(Arrays.asList(course));
        final List<Course> response = courseServiceImpl.getCourses();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test(expected=RuntimeException.class)
    public void getCourseByIdTestError() throws RuntimeException {
        Mockito.when(courseRepository.findById(COURSE_ID)).thenReturn(OPTIONAL_EMPTY);
        courseServiceImpl.getCourseById(COURSE_ID);
        fail();
    }


    @Test
    public void createCourseTest() throws RuntimeException{

        Mockito.when(courseRepository.findById(COURSE_ID)).thenReturn(OPTIONAL_COURSE);
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(COURSE);
        courseServiceImpl.createCourse(CREATE_COURSE_DTO);
    }

    @Test
    public void deleteCourseOk() throws RuntimeException {
        Mockito.when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(COURSE));
        courseRepository.deleteById(COURSE_ID);

    }





}
