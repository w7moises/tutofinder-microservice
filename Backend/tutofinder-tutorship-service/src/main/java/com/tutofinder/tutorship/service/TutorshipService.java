package com.tutofinder.tutorship.service;

import com.tutofinder.tutorship.dto.TutorShipDto;
import com.tutofinder.tutorship.entities.Tutorship;
import org.springframework.data.domain.Pageable;

import java.util.List;

public  interface TutorshipService {
    TutorShipDto getTutorShipById(Long TutorShipId);

    List<TutorShipDto> getTutorShips();

    List<TutorShipDto> getTutorShips(Pageable pageable);

    TutorShipDto createTutorShip(Tutorship tutorShip);

    TutorShipDto updateTutorShip(Tutorship tutorShip, Long TutorShipId);

    String deleteTutorShip(Long TutorShipId);
}


