package com.tutofinder.tutorship.service;

import java.util.List;

import com.tutofinder.tutorship.dto.ReportDto;
import com.tutofinder.tutorship.dto.create.CreateReportDto;

public interface ReportService {
    ReportDto getReportById(Long ReportId);

    List<ReportDto> getReports();

    ReportDto createReport(CreateReportDto createReportDto);

    ReportDto updateReport(CreateReportDto createReportDto, Long ReportId);

    String deleteReport(Long ReportId);
}
