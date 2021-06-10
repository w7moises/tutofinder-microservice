package com.tutofinder.tutorship.service;

import com.tutofinder.tutorship.dto.TutorShipDto;
import com.tutofinder.tutorship.dto.create.CreateTutorShipDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TutorshipService {
    TutorShipDto getTutorShipById(Long TutorShipId);

    List<TutorShipDto> getTutorShips();

    List<TutorShipDto> getTutorShips(Pageable pageable);

    TutorShipDto createTutorShip(CreateTutorShipDto createTutorShipDto);

    TutorShipDto updateTutorShip(CreateTutorShipDto createTutorShipDto, Long TutorShipId);

    String deleteTutorShip(Long TutorShipId);
}
