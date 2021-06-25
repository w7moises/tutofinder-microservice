package com.tutofinder.tutorship.controller;


import java.util.List;

import javax.validation.Valid;

import com.tutofinder.tutorship.dto.ReportDto;
import com.tutofinder.tutorship.dto.create.CreateReportDto;
import com.tutofinder.tutorship.entities.Report;
import com.tutofinder.tutorship.service.ReportService;
import com.tutofinder.tutorship.util.EntityConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin({"http://localhost:4200"})
@Api
@RestController
public class ReportController {
    @Autowired
    private ReportService reportservice;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve all existed reports", notes = "This Operation returns all stored reports.")
    @GetMapping(value = "report")
    public ResponseEntity<List<ReportDto>> findAll() {
        List<Report> reports = reportservice.getReports();
        return new ResponseEntity<>(converter.convertReportToDto(reports), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a father based on Id ", notes = "This Operation returns a father by report Id")
    @GetMapping(value = "report/{reportId}")
    public ResponseEntity<ReportDto> findById(@PathVariable Long reportId) {
        Report report = reportservice.getReportById(reportId);
        return new ResponseEntity<>(converter.convertReportToDto(report), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a  report", notes = "This Operation creates a new report.")
    @PostMapping(value = "report")
    public ResponseEntity<ReportDto> createReport(@Valid CreateReportDto reportDto){
        Report report = reportservice.createReport(reportDto);
        return new ResponseEntity<>(converter.convertReportToDto(report), HttpStatus.CREATED);
    }

    public ResponseEntity<ReportDto> updateReport(@Valid CreateReportDto reportDto, Long reportId){
        Report report = reportservice.updateReport(reportDto,reportId);
        return new ResponseEntity<>(converter.convertReportToDto(report), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes a Report", notes = "This Operation deletes a report.")
    @DeleteMapping(value = "report/{reportId}")
    public String deleteReport(@PathVariable Long reportId) {
        String response = reportservice.deleteReport(reportId);
        return response;
    }
}
