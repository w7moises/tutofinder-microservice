package com.tutofinder.customer.service.impl;

import com.tutofinder.customer.entities.Teacher;
import com.tutofinder.customer.exceptions.TeacherNotFoundException;
import com.tutofinder.customer.repositories.TeacherRepository;
import com.tutofinder.customer.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    @Transactional(readOnly = true)
    public Teacher getTeacherById(Long teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        return teacher.orElseThrow(()->new TeacherNotFoundException(teacherId.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional
    public Teacher createTeacher(Teacher createTeacher, MultipartFile file) throws IOException {
        Teacher newTeacher = Teacher.builder()
                .firstName(createTeacher.getFirstName())
                .lastName(createTeacher.getLastName())
                .dni(createTeacher.getDni())
                .email(createTeacher.getEmail())
                .address(createTeacher.getAddress())
                .depositNumber(createTeacher.getDepositNumber())
                .membership(createTeacher.getMembership())
                .hourlyCost(createTeacher.getHourlyCost())
                .profilePicture(file.getBytes())
                .build();
        return teacherRepository.save(newTeacher);
    }

    @Override
    @Transactional
    public Teacher updateTeacher(Teacher updateTeacher, Long teacherId, MultipartFile file) throws IOException {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if(!teacher.isPresent()){
            throw new TeacherNotFoundException(teacherId.toString());
        }
        Teacher newTeacher = teacher.get();
        newTeacher.setFirstName(updateTeacher.getFirstName());
        newTeacher.setLastName(updateTeacher.getLastName());
        newTeacher.setDni(updateTeacher.getDni());
        newTeacher.setEmail(updateTeacher.getEmail());
        newTeacher.setAddress(updateTeacher.getAddress());
        newTeacher.setDepositNumber(updateTeacher.getDepositNumber());
        newTeacher.setMembership(updateTeacher.getMembership());
        newTeacher.setHourlyCost(updateTeacher.getHourlyCost());
        newTeacher.setProfilePicture(file.getBytes());
        return teacherRepository.save(newTeacher);
    }

    @Override
    public String deleteTeacher(Long teacherId) {
        if(!teacherRepository.existsById(teacherId)){
            throw new TeacherNotFoundException(teacherId.toString());
        }
        teacherRepository.deleteById(teacherId);
        return "Teacher id deleted: "+teacherId;
    }
}
