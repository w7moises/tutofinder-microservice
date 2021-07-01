package com.tutofinder.tutorship.service.impl;

import java.util.List;
import java.util.Optional;

import com.tutofinder.tutorship.client.CustomerServiceClient;
import com.tutofinder.tutorship.dto.CourseDto;
import com.tutofinder.tutorship.dto.TeacherDto;
import com.tutofinder.tutorship.dto.create.CreateTutorShipDto;
import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.entities.TutorShip;
import com.tutofinder.tutorship.exceptions.CourseNotFoundException;
import com.tutofinder.tutorship.exceptions.InternalServerErrorException;
import com.tutofinder.tutorship.exceptions.TeacherNotFoundException;
import com.tutofinder.tutorship.exceptions.TutorShipNotFoundException;
import com.tutofinder.tutorship.repositories.CourseRepository;
import com.tutofinder.tutorship.repositories.TutorShipRepository;
import com.tutofinder.tutorship.service.TutorShipService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TutorShipServiceImpl implements TutorShipService {

    @Autowired
    TutorShipRepository tutorshipRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private CustomerServiceClient customerServiceClient;


    @Override
    @Transactional(readOnly = true)
    public TutorShip getTutorShipById(Long TutorShipId) throws RuntimeException {
        Optional<TutorShip> tutorShip = tutorshipRepository.findById(TutorShipId);
        return tutorShip.orElseThrow(()-> new TutorShipNotFoundException(TutorShipId.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TutorShip> getTutorShips() {
       return tutorshipRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public TutorShip createTutorShip(CreateTutorShipDto tutorShip) throws RuntimeException {
        TutorShip newtutor = new TutorShip();
        TeacherDto teacherDto = customerServiceClient.findTeacherById(tutorShip.getTeacherId())
                .orElseThrow(()-> new TeacherNotFoundException("TEACHER_NOTFOUND"));

        Course course = courseRepository.findById(tutorShip.getCourseId())
                .orElseThrow(()-> new CourseNotFoundException("COURSE_NOTFOUND"));

        //newtutor.setReport(tutorShip.getReport());
        try{
            Long id;
            newtutor.setMinutes(tutorShip.getMinutesAmmount());
            newtutor.setDescription(tutorShip.getTutorShipDescription());
            newtutor.setTeacherId(tutorShip.getTeacherId());
            newtutor.setCourse(course);
            newtutor = tutorshipRepository.save(newtutor);
            return  newtutor;

        } catch (Exception e){
            throw  new InternalServerErrorException("INTERNAL_SERVER_ERROR");
        }
        //return getTutorShipById(id);
    }

    @Override
    public TutorShip updateTutorShip(CreateTutorShipDto tutorShip, Long tutorShipId) throws RuntimeException {
        Optional<TutorShip> tutorShip1 = tutorshipRepository.findById(tutorShipId);
        TeacherDto teacherDto = customerServiceClient.findTeacherById(tutorShip.getTeacherId())
                .orElseThrow(() -> new TeacherNotFoundException("TEACHER_NOTFOUND"));

        Course course = courseRepository.findById(tutorShip.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("COURSE_NOTFOUND"));

        if (!tutorShip1.isPresent()) {
            throw new TutorShipNotFoundException(tutorShipId.toString());
        }
        TutorShip newtutorship = tutorShip1.get();
        newtutorship.setTeacherId(tutorShip.getTeacherId());
        newtutorship.setDescription(tutorShip.getTutorShipDescription());
        newtutorship.setMinutes(tutorShip.getMinutesAmmount());
        newtutorship.setCourse(course);

        try{
            tutorshipRepository.save(newtutorship);
        } catch(Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR");

        }
        return getTutorShipById(tutorShipId);
    }

    @Override
    public String deleteTutorShip(Long TutorShipId) {
        tutorshipRepository.findById(TutorShipId)
                .orElseThrow(()-> new InternalServerErrorException("ID_NOTFOUND"));
        try{
            tutorshipRepository.deleteById(TutorShipId);
        } catch(Exception e) {
            throw new InternalServerErrorException("ID_NOTFOUND");
        }
        return "TUTORSHIP_DELETE";
    }
}
