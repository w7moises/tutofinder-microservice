package com.tutofinder.tutorship.service.impl;

import java.util.List;


import com.tutofinder.tutorship.client.CustomerServiceClient;
import com.tutofinder.tutorship.dto.TutorShipDto;
import com.tutofinder.tutorship.entities.Tutorship;
import com.tutofinder.tutorship.repositories.CourseRepository;
import com.tutofinder.tutorship.repositories.TutorshipRepository;
import com.tutofinder.tutorship.service.TutorshipService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TutorShipServiceImpl implements TutorshipService {

    @Autowired
    TutorshipRepository tutorshipRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Override
    public TutorShipDto getTutorShipById(Long TutorShipId) {
        return null;
    }

    @Override
    public List<TutorShipDto> getTutorShips() {
        return null;
    }

    @Override
    public List<TutorShipDto> getTutorShips(Pageable pageable) {
        return null;
    }

    @Override
    public TutorShipDto createTutorShip(Tutorship tutorShip) {
        return null;
    }

    @Override
    public TutorShipDto updateTutorShip(Tutorship tutorShip, Long TutorShipId) {
        return null;
    }

    @Override
    public String deleteTutorShip(Long TutorShipId) {
        return null;
    }
}
