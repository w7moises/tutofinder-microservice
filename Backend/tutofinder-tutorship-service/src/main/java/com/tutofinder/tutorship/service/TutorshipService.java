package com.tutofinder.tutorship.service;

import java.util.List;

import com.tutofinder.tutorship.dto.create.CreateTutorShipDto;
import com.tutofinder.tutorship.entities.TutorShip;

import org.springframework.data.domain.Pageable;

public interface TutorShipService {
    TutorShip getTutorShipById(Long TutorShipId);

    List<TutorShip> getTutorShips();

    List<TutorShip> getTutorShips(Pageable pageable);

    TutorShip createTutorShip(CreateTutorShipDto tutorShip);

    TutorShip updateTutorShip(CreateTutorShipDto tutorShip, Long tutorShipId);

    String deleteTutorShip(Long TutorShipId);
}
