package com.tutofinder.customer.service.impl;

import com.tutofinder.customer.entities.Father;
import com.tutofinder.customer.exceptions.FatherNotFoundException;
import com.tutofinder.customer.repositories.FatherRepository;
import com.tutofinder.customer.service.FatherService;
import com.tutofinder.customer.util.EntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Father getFatherById(Long fatherId) {
        Optional<Father> father = fatherRepository.findById(fatherId);
        return father.orElseThrow(()->new FatherNotFoundException(fatherId.toString()));
    }

    @Override
    public List<Father> getFathers() {
        return fatherRepository.findAll();
    }

    @Override
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
    public Father updateFather(Father updateFather, Long fatherId, MultipartFile file) throws IOException {
        Optional<Father> father = fatherRepository.findById(fatherId);
        if(!father.isPresent()){
            throw new FatherNotFoundException(fatherId.toString());
        }
        Father newFather = father.get();
        newFather = Father.builder()
                .firstName(updateFather.getFirstName())
                .lastName(updateFather.getLastName())
                .dni(updateFather.getDni())
                .email(updateFather.getEmail())
                .address(updateFather.getAddress())
                .profilePicture(file.getBytes())
                .build();
        return fatherRepository.save(newFather);
    }

    @Override
    public String deleteFather(Long fatherId) {
        if(!fatherRepository.existsById(fatherId)){
            throw new FatherNotFoundException(fatherId.toString());
        }
        fatherRepository.deleteById(fatherId);
        return "Father"+fatherId +"deleted";
    }
}
