package com.tutofinder.tutorship.service;

import com.tutofinder.tutorship.client.CustomerServiceClient;
import com.tutofinder.tutorship.dto.TeacherDto;
import com.tutofinder.tutorship.dto.TutorShipDto;
import com.tutofinder.tutorship.dto.create.CreateTutorShipDto;
import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.entities.TutorShip;
import com.tutofinder.tutorship.repositories.CourseRepository;
import com.tutofinder.tutorship.repositories.TutorShipRepository;
import com.tutofinder.tutorship.service.impl.TutorShipServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

public class TutorshipServiceTest {

    private static final Long TUTORSHIP_ID = 1L;
    private static final String DESCRIPTION_TUTORSHIP = "DESCRIPTION";
    private static final Double AMOUNT_HOURS = 45.8;
    private static int CANTIDAD_HORAS;
    private static final Long COURSE_ID = 1L;
    private static final Long TEACHER_ID = 1L;
    public static final TutorShip TUTORSHIP = new TutorShip();
    public static final Course COURSE = new Course();

    private static CreateTutorShipDto CREATE_TUTORSHIP_DTO = new CreateTutorShipDto();
    private static final Optional<TutorShip> OPTIONAL_EMPTY = Optional.empty();
    private static final Optional<TutorShip> OPTIONAL_TUTORSHIP = Optional.of(TUTORSHIP);
    private static final Optional<TeacherDto> OPTIONAL_TEACHER = Optional.of(new TeacherDto());
    private static Course course = new Course();
    //private static final Optional<TutorShipDto> OPTIONAL_TEACHER = Optional.of(new TeacherDto());

    @InjectMocks
    TutorShipServiceImpl tutorShipService;

    @Mock
    TutorShipRepository tutorShipRepository;
    @Mock
    CourseRepository courseRepository;

    @Mock
    CustomerServiceClient customerServiceClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        TUTORSHIP.setCourse(course);
        TUTORSHIP.setDescription(DESCRIPTION_TUTORSHIP);
        TUTORSHIP.setId(TUTORSHIP_ID);
        TUTORSHIP.setMinutes(CANTIDAD_HORAS);

        CREATE_TUTORSHIP_DTO.setMinutesAmmount(CANTIDAD_HORAS);
        CREATE_TUTORSHIP_DTO.setCourseId(COURSE_ID);
        CREATE_TUTORSHIP_DTO.setTutorShipDescription(DESCRIPTION_TUTORSHIP);
        CREATE_TUTORSHIP_DTO.setTeacherId(TEACHER_ID);

    }
    @Test
    public void getTutorshipByIdTest() throws RuntimeException {
        Mockito.when(tutorShipRepository.findById(TUTORSHIP_ID)).thenReturn(OPTIONAL_TUTORSHIP);
        tutorShipService.getTutorShipById(TUTORSHIP_ID);
    }
    @Test
    public void getTutorshipTest() throws RuntimeException {
        final TutorShip tutorShip = new TutorShip();
        Mockito.when(tutorShipRepository.findAll()).thenReturn(Arrays.asList(tutorShip));
        final List<TutorShip> response = tutorShipService.getTutorShips();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);

    }

    @Test
    public void createTutorshipTest() throws RuntimeException {
        Mockito.when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(COURSE));
        Mockito.when(customerServiceClient.findTeacherById(TEACHER_ID)).thenReturn(OPTIONAL_TEACHER);
        Mockito.when(tutorShipRepository.findById(TUTORSHIP_ID)).thenReturn(Optional.of(TUTORSHIP));
        Mockito.when(tutorShipRepository.save(Mockito.any(TutorShip.class))).thenReturn(TUTORSHIP);
        tutorShipService.createTutorShip(CREATE_TUTORSHIP_DTO);
    }


    @Test
    public void deleteTutorshipTest() throws RuntimeException {
        Mockito.when(tutorShipRepository.findById(TUTORSHIP_ID)).thenReturn(Optional.of(TUTORSHIP));
        tutorShipService.deleteTutorShip(TUTORSHIP_ID);
    }







}
