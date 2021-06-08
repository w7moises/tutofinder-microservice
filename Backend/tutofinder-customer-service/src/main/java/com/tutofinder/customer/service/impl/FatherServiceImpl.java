package com.tutofinder.customer.service.impl;

import com.tutofinder.customer.entities.Father;
import com.tutofinder.customer.exceptions.FatherNotFoundException;
import com.tutofinder.customer.repositories.FatherRepository;
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

    @Override
    @Transactional(readOnly = true)
    public Father getFatherById(Long fatherId) {
        Optional<Father> father = fatherRepository.findById(fatherId);
        return father.orElseThrow(()->new FatherNotFoundException(fatherId.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Father> getFathers() {
        return fatherRepository.findAll();
    }

    @Override
    @Transactional
    public Father createFather(Father createFather, MultipartFile file) throws IOException {
        Father newFather = Father.builder()
                .firstName(createFather.getFirstName())
                .lastName(createFather.getLastName())
                .dni(createFather.getDni())
                .email(createFather.getEmail())
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
}
