package com.tutofinder.tutorship.service.impl;

import java.util.List;
import java.util.Optional;

import com.sun.jdi.InternalException;
import com.tutofinder.tutorship.client.CustomerServiceClient;
import com.tutofinder.tutorship.dto.StudentDto;
import com.tutofinder.tutorship.dto.create.CreateReportDto;
import com.tutofinder.tutorship.entities.Report;
import com.tutofinder.tutorship.entities.TutorShip;
import com.tutofinder.tutorship.exceptions.CourseNotFoundException;
import com.tutofinder.tutorship.exceptions.ReportInternalServerException;
import com.tutofinder.tutorship.exceptions.ReportNotFoundException;
import com.tutofinder.tutorship.exceptions.StudentNotFoundException;
import com.tutofinder.tutorship.repositories.ReportRepository;
import com.tutofinder.tutorship.repositories.TutorShipRepository;
import com.tutofinder.tutorship.service.ReportService;
import com.tutofinder.tutorship.util.ExceptionMessagesEnum;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TutorShipRepository tutorShipRepository;

    @Autowired
    CustomerServiceClient customerServiceClient;

    @Override
    @Transactional
    public Report getReportById(Long ReportId) {
        Optional<Report> teacher = reportRepository.findById(ReportId);
        return teacher.orElseThrow(() -> new ReportNotFoundException(ReportId.toString()));
    }

    @Override

    public List<Report> getReports() {
        return reportRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Report createReport(CreateReportDto reportDto)  throws RuntimeException {

        //studentDto lo jalamos solo para verificar si existe
        StudentDto studentDto = customerServiceClient.findStudentById(reportDto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(ExceptionMessagesEnum.STUDENT_NOT_FOUND.getValue()));
        TutorShip tutorShip = tutorShipRepository.findById(reportDto.getTutorShipId())
                .orElseThrow(() -> new CourseNotFoundException(ExceptionMessagesEnum.TUTORSHIP_NOT_FOUND.getValue()));
      try{
          Report newReport = Report.builder()
                  .descriptionReport(reportDto.getDescriptionReport())
                  .studentId(reportDto.getStudentId())
                  .tutorShip(tutorShip)
                  .build();
          return reportRepository.save(newReport);
      } catch (Exception e) {
          throw new ReportInternalServerException("INTERNAL_SERVER_ERROR");
      }

    }

    @Override
    public Report updateReport(CreateReportDto reportDto, Long ReportId)  throws RuntimeException {
        Report report1 =reportRepository.findById(ReportId).orElseThrow(
                ()-> new ReportNotFoundException("Report doesn't found")
        );

        StudentDto studentDto = customerServiceClient.findStudentById(reportDto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(ExceptionMessagesEnum.STUDENT_NOT_FOUND.getValue()));
        TutorShip tutorShip = tutorShipRepository.findById(reportDto.getTutorShipId())
                .orElseThrow(() -> new CourseNotFoundException(ExceptionMessagesEnum.TUTORSHIP_NOT_FOUND.getValue()));

        if (!reportRepository.findById(ReportId).isPresent()) {
            throw new ReportNotFoundException(ReportId.toString());
        }

        Long id;
        report1.setDescriptionReport(reportDto.getDescriptionReport());

        try {
            id = reportRepository.save(report1).getId();
        } catch (Exception e) {
            throw  new InternalException(
                    "INTERNAL SERVER ERROR"
            );
        }
        return getReportById(id);
    }

    @Override
    public String deleteReport(Long ReportId) {
        if(!reportRepository.existsById(ReportId)){
            throw new StudentNotFoundException("REPORT_NOT_FOUND");
        }
        reportRepository.deleteById(ReportId);
        return "Student id deleted: "+ ReportId;
    }

}
