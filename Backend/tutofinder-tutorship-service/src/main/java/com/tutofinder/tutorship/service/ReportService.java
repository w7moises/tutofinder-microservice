package com.tutofinder.tutorship.service;

import java.util.List;

import com.tutofinder.tutorship.dto.create.CreateReportDto;
import com.tutofinder.tutorship.entities.Report;

import org.springframework.web.multipart.MultipartFile;

public interface ReportService {
    Report getReportById(Long ReportId);

    List<Report> getReports();

    Report createReport(CreateReportDto reportDto)  throws RuntimeException;

    Report updateReport(CreateReportDto reportDto, Long ReportId)  throws RuntimeException;

    String deleteReport(Long ReportId);
}
