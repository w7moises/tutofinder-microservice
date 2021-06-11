package com.tutofinder.tutorship.service.impl;

import java.util.List;


import com.tutofinder.tutorship.client.CustomerServiceClient;
import com.tutofinder.tutorship.dto.TutorShipDto;
import com.tutofinder.tutorship.dto.create.CreateTutorShipDto;
import com.tutofinder.tutorship.entities.TutorShip;
import com.tutofinder.tutorship.repositories.CourseRepository;
import com.tutofinder.tutorship.repositories.TutorShipRepository;
import com.tutofinder.tutorship.service.TutorShipService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TutorShipServiceImpl implements TutorShipService {

    @Autowired
    TutorShipRepository tutorshipRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Override
    public TutorShipDto getTutorShipById(Long TutorShipId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TutorShipDto> getTutorShips() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TutorShipDto> getTutorShips(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TutorShipDto createTutorShip(CreateTutorShipDto tutorShip) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TutorShipDto updateTutorShip(CreateTutorShipDto tutorShip, Long TutorShipId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteTutorShip(Long TutorShipId) {
        // TODO Auto-generated method stub
        return null;
    }

}
