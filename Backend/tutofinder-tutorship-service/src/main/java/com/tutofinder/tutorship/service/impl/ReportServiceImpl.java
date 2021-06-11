package com.tutofinder.tutorship.service.impl;

import com.tutofinder.tutorship.client.CustomerServiceClient;
import com.tutofinder.tutorship.dto.create.CreateReportDto;
import com.tutofinder.tutorship.entities.Report;
import com.tutofinder.tutorship.exceptions.ReportNotFoundException;
import com.tutofinder.tutorship.repositories.ReportRepository;
import com.tutofinder.tutorship.repositories.TutorshipRepository;
import com.tutofinder.tutorship.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TutorshipRepository tutorshipRepository;

    @Autowired
    CustomerServiceClient customerServiceClient;


    @Override
    @Transactional
    public Report getReportById(Long ReportId) {
        Optional<Report> report = reportRepository.findById(ReportId);
        return  report.orElseThrow(()->new ReportNotFoundException(ReportId.toString()));
    }

    @Override
    @Transactional
    public List<Report> getReports() {
        return reportRepository.findAll();
    }

    @Override
    @Transactional
    public Report createReport(CreateReportDto createReportDto) {
        Report newReport = Report.builder()
                .descriptionReport(createReportDto.getDescriptionReport())
                .status(createReportDto.getStatus())
                //.student(c)
                .build();
        return reportRepository.save(newReport);
    }

    @Override
    @Transactional
    public Report updateReport(Report updateReport, Long ReportId, MultipartFile file) {
        Optional<Report> report = reportRepository.findById(ReportId);
        if(!report.isPresent()){
            throw new ReportNotFoundException(ReportId.toString());
        }
        Report newReport = report.get();
        newReport.setDescriptionReport(updateReport.getDescriptionReport());
        return reportRepository.save(newReport);
    }

    @Override
    @Transactional
    public String deleteReport(Long ReportId) {
        if(!reportRepository.existsById(ReportId)){
            throw new ReportNotFoundException(ReportId.toString());
        }
        reportRepository.deleteById(ReportId);
        return "Report id deleted: "+ReportId;
    }
}
