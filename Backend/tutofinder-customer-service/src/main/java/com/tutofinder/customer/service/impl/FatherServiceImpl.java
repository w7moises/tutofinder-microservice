package com.tutofinder.customer.service.impl;

import com.tutofinder.customer.entities.Father;
import com.tutofinder.customer.entities.Teacher;
import com.tutofinder.customer.entities.User;
import com.tutofinder.customer.exceptions.FatherNotFoundException;
import com.tutofinder.customer.repositories.FatherRepository;
import com.tutofinder.customer.repositories.TeacherRepository;
import com.tutofinder.customer.repositories.UserRepository;
import com.tutofinder.customer.service.FatherService;
import com.tutofinder.customer.util.EntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FatherServiceImpl implements FatherService {

    @Autowired
    private FatherRepository fatherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    @Transactional(readOnly = true)
    public Father getFatherById(Long fatherId) {
        Optional<Father> father = fatherRepository.findById(fatherId);
        return father.orElseThrow(()->new FatherNotFoundException(fatherId.toString()));
    }

    @Override
    public Father getFatherByEmail(String email) {
        Optional<Father> father = fatherRepository.findByEmail(email);
        return father.orElseThrow(()->new FatherNotFoundException(email));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Father> getFathers() {
        return fatherRepository.findAll();
    }

    @Override
    @Transactional
    public Father createFather(Father createFather, MultipartFile file,String username) throws IOException {
        User user = userRepository.findByUsername(username);
        Father newFather = Father.builder()
                .firstName(createFather.getFirstName())
                .lastName(createFather.getLastName())
                .dni(createFather.getDni())
                .email(user.getEmail())
                .address(createFather.getAddress())
                .profilePicture(file.getBytes())
                .build();
        return fatherRepository.save(newFather);
    }

    @Override
    @Transactional
    public Father updateFather(Father updateFather, Long fatherId, MultipartFile file) throws IOException {
        Optional<Father> father = fatherRepository.findById(fatherId);
        if(!father.isPresent()){
            throw new FatherNotFoundException(fatherId.toString());
        }
        Father newFather = father.get();
        newFather.setFirstName(updateFather.getFirstName());
        newFather.setLastName(updateFather.getLastName());
        newFather.setDni(updateFather.getDni());
        newFather.setEmail(updateFather.getEmail());
        newFather.setProfilePicture(file.getBytes());
        newFather.setAddress(updateFather.getAddress());
        return fatherRepository.save(newFather);
    }

    @Override
    public String deleteFather(Long fatherId) {
        if(!fatherRepository.existsById(fatherId)){
            throw new FatherNotFoundException(fatherId.toString());
        }
        fatherRepository.deleteById(fatherId);
        return "Father id deleted: "+fatherId;
    }

    @Override
    @Transactional
    public Father registerFavorite(Long fatherId,Long teacherId) {
        Optional<Father> father = fatherRepository.findById(fatherId);
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        Father updateFather = father.get();
        Teacher updateTeacher = teacher.get();
        if(father.isPresent() && teacher.isPresent()){
            fatherRepository.registerFavorite(updateFather.getId(), updateTeacher.getId());
        }
        return updateFather;
    }

    @Override
    @Transactional
    public String deleteFavorite(Long fatherId ,Long teacherId) {
        Optional<Father> father = fatherRepository.findById(fatherId);
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        Father updateFather = father.get();
        Teacher updateTeacher = teacher.get();
        if(father.isPresent() && teacher.isPresent()){
            fatherRepository.deleteFavorite(updateFather.getId(),updateTeacher.getId());
        }
        return "Teacher deleted:  " + updateTeacher.getId();
    }
}
