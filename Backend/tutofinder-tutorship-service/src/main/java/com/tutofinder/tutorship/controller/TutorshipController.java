package com.tutofinder.tutorship.controller;

import java.util.List;

import javax.validation.Valid;

import com.tutofinder.tutorship.dto.TutorShipDto;
import com.tutofinder.tutorship.dto.create.CreateTutorShipDto;
import com.tutofinder.tutorship.entities.TutorShip;
import com.tutofinder.tutorship.service.TutorShipService;
import com.tutofinder.tutorship.util.EntityConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin({ "http://localhost:4200" })
@Api
@RestController
public class TutorShipController {

    @Autowired
    TutorShipService tutorShipService;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve a tutorShip based on Id ", notes = "This Operation returns a tutorShip by TutorShip Id")
    @GetMapping(value = "tutorShip/{tutorShipId}")
    public ResponseEntity<TutorShipDto> findById(@PathVariable Long tutorShipId) {
        TutorShip tutorShip = tutorShipService.getTutorShipById(tutorShipId);
        return new ResponseEntity<>(converter.convertTutorShipToDto(tutorShip), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve all existing tutorShips", notes = "This Operation returns all stored tutorShips.")
    @GetMapping(value = "tutorShip")
    public ResponseEntity<List<TutorShipDto>> findAll() {
        List<TutorShip> tutorShips = tutorShipService.getTutorShips();
        return new ResponseEntity<>(converter.convertTutorShipToDto(tutorShips), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve all existing tutorShips with pageable", notes = "This Operation returns all stored tutorShips with pageable.")
    @GetMapping(value = "tutorShip/page")
    public ResponseEntity<List<TutorShipDto>> findAll(Pageable pageable) {
        List<TutorShip> tutorShips = tutorShipService.getTutorShips(pageable);
        return new ResponseEntity<>(converter.convertTutorShipToDto(tutorShips), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a tutorShip", notes = "This Operation creates a new tutorShip.")
    @PostMapping(value = "tutorShip")
    public ResponseEntity<TutorShipDto> createTutorShip(@Valid CreateTutorShipDto tutorShipDto) {
        TutorShip tutorShip = tutorShipService.createTutorShip(tutorShipDto);
        return new ResponseEntity<>(converter.convertTutorShipToDto(tutorShip), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Modifies a tutorShip", notes = "This Operation modifies a tutorShip.")
    @PutMapping(value = "tutorShip/{tutorShipId}")
    public ResponseEntity<TutorShipDto> updateTutorShip(@Valid CreateTutorShipDto tutorShipDto,
            @PathVariable Long tutorShipId) {
        TutorShip tutorShip = tutorShipService.updateTutorShip(tutorShipDto, tutorShipId);
        return new ResponseEntity<>(converter.convertTutorShipToDto(tutorShip), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes a tutorShip", notes = "This Operation deletes a tutorShip.")
    @DeleteMapping(value = "tutorShip/{tutorShipId}")
    public String deleteTutorShip(@PathVariable Long tutorShipId) {
        String response = tutorShipService.deleteTutorShip(tutorShipId);
        return response;
    }
}
