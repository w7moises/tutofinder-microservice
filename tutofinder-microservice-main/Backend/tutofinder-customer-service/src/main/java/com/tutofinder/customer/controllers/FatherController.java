package com.tutofinder.customer.controllers;

import com.tutofinder.customer.dto.FatherDto;
import com.tutofinder.customer.entities.Father;
import com.tutofinder.customer.service.FatherService;
import com.tutofinder.customer.util.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Api
@RestController
public class FatherController {
    @Autowired
    private FatherService fatherService;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve all existed fathers", notes = "This Operation returns all stored fathers.")
    @GetMapping(value = "father")
    public ResponseEntity<List<FatherDto>> findAll() {
        List<Father> fathers = fatherService.getFathers();
        return new ResponseEntity<>(converter.convertEntityToDto(fathers), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a father based on Id ", notes = "This Operation returns a father by Father Id")
    @GetMapping(value = "father/{fatherId}")
    public ResponseEntity<FatherDto> findById(@PathVariable Long fatherId) {
        Father father = fatherService.getFatherById(fatherId);
        return new ResponseEntity<>(converter.convertEntityToDto(father), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a father", notes = "This Operation creates a new father.")
    @PostMapping(value = "father")
    public ResponseEntity<FatherDto> createFather(@Valid FatherDto fatherDto, @RequestParam MultipartFile file) throws IOException{
        Father father = converter.convertFatherToEntity(fatherDto);
        father = fatherService.createFather(father,file);
        return new ResponseEntity<>(converter.convertEntityToDto(father), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Modifies a father", notes = "This Operation modifies a father.")
    @PutMapping(value = "father/{fatherId}")
    public ResponseEntity<FatherDto> updateFather(@Valid FatherDto fatherDto,@PathVariable Long fatherId, @RequestParam MultipartFile file) throws IOException{
        Father father = converter.convertFatherToEntity(fatherDto);
        father = fatherService.updateFather(father,fatherId,file);
        return new ResponseEntity<>(converter.convertEntityToDto(father), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes a father", notes = "This Operation deletes a father.")
    @DeleteMapping(value = "father/{fatherId}")
    public String deleteFather(@PathVariable Long fatherId) {
        String response = fatherService.deleteFather(fatherId);
        return response;
    }
}
