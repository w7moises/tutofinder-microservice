package com.tutofinder.tutorship.controller;


import com.tutofinder.tutorship.dto.ReportDto;
import com.tutofinder.tutorship.dto.create.CreateReportDto;
import com.tutofinder.tutorship.repositories.ReportRepository;
import com.tutofinder.tutorship.util.EntityConverter;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@Api
@RestController
public class ReportController {
    @Autowired
    private ReportRepository repository;

    @Autowired
    private EntityConverter converter;

    public ResponseEntity<List<ReportDto>> findAll(){
     return null;
    }

    public ResponseEntity<ReportDto> createReport(@Validated @RequestBody CreateReportDto reportDto){
     return null;
    }

    public String deleteReport(@PathVariable Long reportId) {
     return null;
    }



    
}
