package com.tutofinder.tutorship.service;

import java.util.List;

import com.tutofinder.tutorship.dto.ReportDto;
import com.tutofinder.tutorship.dto.create.CreateReportDto;
import com.tutofinder.tutorship.entities.Report;

import org.springframework.web.multipart.MultipartFile;

public interface ReportService {
    Report getReportById(Long ReportId);

    List<Report> getReports();

    ReportDto createReport(CreateReportDto createReportDto);

    ReportDto updateReport(Report updateReport, Long ReportId, MultipartFile file);

    String deleteReport(Long ReportId);
}
