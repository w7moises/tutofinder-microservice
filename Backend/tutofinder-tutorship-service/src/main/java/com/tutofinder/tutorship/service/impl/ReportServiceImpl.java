package com.tutofinder.tutorship.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.tutofinder.tutorship.client.CustomerServiceClient;
import com.tutofinder.tutorship.dto.StudentDto;
import com.tutofinder.tutorship.dto.create.CreateReportDto;
import com.tutofinder.tutorship.entities.Report;
import com.tutofinder.tutorship.entities.TutorShip;
import com.tutofinder.tutorship.exceptions.CourseNotFoundException;
import com.tutofinder.tutorship.exceptions.ReportNotFoundException;
import com.tutofinder.tutorship.exceptions.StudentNotFoundException;
import com.tutofinder.tutorship.repositories.ReportRepository;
import com.tutofinder.tutorship.repositories.TutorShipRepository;
import com.tutofinder.tutorship.service.ReportService;
import com.tutofinder.tutorship.util.ExceptionMessagesEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Transactional(readOnly = true)
    public Report getReportById(Long ReportId) {
        Optional<Report> teacher = reportRepository.findById(ReportId);
        return teacher.orElseThrow(() -> new ReportNotFoundException(ReportId.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Report> getReports() {

        return reportRepository.findAll();
    }

    @Override
    public Report createReport(CreateReportDto reportDto) {
        //studentDto lo jalamos solo para verificar si existe
        StudentDto studentDto = customerServiceClient.findStudentById(reportDto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(ExceptionMessagesEnum.STUDENT_NOT_FOUND.getValue()));
        TutorShip tutorShip = tutorShipRepository.findById(reportDto.getTutorShipId())
                .orElseThrow(() -> new CourseNotFoundException(ExceptionMessagesEnum.COURSE_NOT_FOUND.getValue()));
        Report newReport = Report.builder()
        .descriptionReport(reportDto.getDescriptionReport())
        .studentId(reportDto.getStudentId())
        .tutorShip(tutorShip)
        .build();
        return reportRepository.save(newReport);
    }

    @Override
    public Report updateReport(CreateReportDto reportDto, Long ReportId, MultipartFile file) {
        return null;
    }

    @Override
    public String deleteReport(Long ReportId) {
        return null;
    }

    /*
     * @Override
     * 
     * @Transactional public Report getReportById(Long ReportId) { Optional<Report>
     * report = reportRepository.findById(ReportId); return
     * report.orElseThrow(()->new ReportNotFoundException(ReportId.toString())); }
     * 
     * @Override
     * 
     * @Transactional public List<Report> getReports() { return
     * reportRepository.findAll(); }
     * 
     * @Override
     * 
     * @Transactional public Report createReport(Report report) { Report newReport =
     * Report.builder() .descriptionReport(report.getDescriptionReport())
     * .status(report.getStatus()) .student(report.getStudent()) .build(); return
     * reportRepository.save(newReport); }
     * 
     * @Override
     * 
     * @Transactional public Report updateReport(Report updateReport, Long ReportId,
     * MultipartFile file) { Optional<Report> report =
     * reportRepository.findById(ReportId); if(!report.isPresent()){ throw new
     * ReportNotFoundException(ReportId.toString()); } Report newReport =
     * report.get();
     * newReport.setDescriptionReport(updateReport.getDescriptionReport()); return
     * reportRepository.save(newReport); }
     * 
     * @Override
     * 
     * @Transactional public String deleteReport(Long ReportId) {
     * if(!reportRepository.existsById(ReportId)){ throw new
     * ReportNotFoundException(ReportId.toString()); }
     * reportRepository.deleteById(ReportId); return "Report id deleted: "+ReportId;
     * }
     * 
     * 
     */
}
