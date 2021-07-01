package com.tutofinder.customer.controllers;

import com.tutofinder.customer.dto.FatherDto;
import com.tutofinder.customer.dto.create.CreateFatherDto;
import com.tutofinder.customer.entities.Father;
import com.tutofinder.customer.service.FatherService;
import com.tutofinder.customer.util.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@Api
@RestController
public class FatherController {
    @Autowired
    private FatherService fatherService;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve a picture from the father", notes = "This Operation returns a father's picture.")
    @GetMapping("/father/img/{fatherId}")
    public ResponseEntity<?> findPicture(@PathVariable Long fatherId){
        Father father = fatherService.getFatherById(fatherId);
        Resource picture = new ByteArrayResource(father.getProfilePicture());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(picture);
    }

    @ApiOperation(value = "Retrieve all existed fathers", notes = "This Operation returns all stored fathers.")
    @GetMapping(value = "father")
    public ResponseEntity<List<FatherDto>> findAll() {
        List<Father> fathers = fatherService.getFathers();
        return new ResponseEntity<>(converter.convertFatherToDto(fathers), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a father by email", notes = "This Operation return a father by email.")
    @GetMapping(value = "father/email/{email}")
    public ResponseEntity<FatherDto> findByEmail(@PathVariable String email) {
        Father father = fatherService.getFatherByEmail(email);
        return new ResponseEntity<>(converter.convertFatherToDto(father), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a father based on Id ", notes = "This Operation returns a father by Father Id")
    @GetMapping(value = "father/{fatherId}")
    public ResponseEntity<FatherDto> findById(@PathVariable Long fatherId) {
        Father father = fatherService.getFatherById(fatherId);
        return new ResponseEntity<>(converter.convertFatherToDto(father), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a father", notes = "This Operation creates a new father.")
    @PostMapping(value = "father/{username}")
    public ResponseEntity<FatherDto> createFather(@RequestBody @Valid CreateFatherDto fatherDto,@PathVariable String username) throws IOException{
        Father father = converter.convertCreateFatherToEntity(fatherDto);
        father = fatherService.createFather(father,username);
        return new ResponseEntity<>(converter.convertFatherToDto(father), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Modifies a father", notes = "This Operation modifies a father.")
    @PutMapping(value = "father/{fatherId}")
    public ResponseEntity<FatherDto> updateFather(@Valid CreateFatherDto fatherDto,@PathVariable Long fatherId, @RequestParam MultipartFile file) throws IOException{
        Father father = converter.convertCreateFatherToEntity(fatherDto);
        father = fatherService.updateFather(father,fatherId,file);
        return new ResponseEntity<>(converter.convertFatherToDto(father), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes a father", notes = "This Operation deletes a father.")
    @DeleteMapping(value = "father/{fatherId}")
    public String deleteFather(@PathVariable Long fatherId) {
        String response = fatherService.deleteFather(fatherId);
        return response;
    }

    @ApiOperation(value = "Create a favorite", notes = "This Operation creates a new favorite.")
    @PutMapping(value = "father/{fatherId}/favorite/{teacherId}")
    public ResponseEntity<FatherDto> createFavorite(@PathVariable Long fatherId,@PathVariable Long teacherId){
        Father father = fatherService.registerFavorite(fatherId, teacherId);
        return new ResponseEntity<>(converter.convertFatherToDto(father), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a favorite", notes = "This Operation deletes a favorite.")
    @DeleteMapping(value = "father/{fatherId}/favorite/{teacherId}")
    public ResponseEntity<String> deleteFavorite(@PathVariable Long fatherId,@PathVariable Long teacherId){
        String response = fatherService.deleteFavorite(fatherId, teacherId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
