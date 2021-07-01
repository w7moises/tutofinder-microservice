package com.tutofinder.tutorship.service;

import com.tutofinder.tutorship.client.CustomerServiceClient;
import com.tutofinder.tutorship.dto.ReportDto;
import com.tutofinder.tutorship.dto.StudentDto;
import com.tutofinder.tutorship.dto.create.CreateReportDto;
import com.tutofinder.tutorship.entities.Report;
import com.tutofinder.tutorship.entities.TutorShip;
import com.tutofinder.tutorship.repositories.ReportRepository;
import com.tutofinder.tutorship.repositories.TutorShipRepository;
import com.tutofinder.tutorship.service.impl.ReportServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ReportServiceTest {
    private static final Long INFORME_ID = 1L;
    private static final String DESCRIPCION_INFORME = "bueno";
    private static final Long TUTORIA_ID = 1L;
    private static final Long ALUMNO_ID = 1L;
    public static final Report REPORT = new Report();

    CreateReportDto CREATE_INFORME_DTO = new CreateReportDto();
    private static final String INFORME_DELETED = "INFORME_DELETED";
    private static final Optional<Report> OPTIONAL_REPORT_EMPTY = Optional.empty();
    private static final Optional<Report> OPTIONAL_REPORT = Optional.of(REPORT);
    private static final Optional<TutorShip> OPTIONAL_TUTORIA = Optional.of(new TutorShip());
    private static final Optional<StudentDto> OPTIONAL_ALUMNO = Optional.of(new StudentDto());

    @Mock
    ReportRepository reportRepository;

    @Mock
    TutorShipRepository tutoriaRepository;


    @Mock
    CustomerServiceClient customerServiceClient;

    @InjectMocks
    ReportServiceImpl informeServiceImpl;

    @Before
    public void init() throws RuntimeException {
        MockitoAnnotations.initMocks(this);

        REPORT.setId(INFORME_ID);
        REPORT.setDescriptionReport(DESCRIPCION_INFORME);

        CREATE_INFORME_DTO.setDescriptionReport(DESCRIPCION_INFORME);
        CREATE_INFORME_DTO.setStudentId(TUTORIA_ID);
        CREATE_INFORME_DTO.setTutorShipId(ALUMNO_ID);
    }

    @Test
    public void getInformeByIdTest() throws RuntimeException{
        Mockito.when(reportRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_REPORT);
        informeServiceImpl.getReportById(INFORME_ID);
    }

    @Test
    public void getInformesTest() throws RuntimeException{
        final Report informe = new Report();
        Mockito.when(reportRepository.findAll()).thenReturn(Arrays.asList(informe));
        final List<Report> response = informeServiceImpl.getReports();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test(expected = RuntimeException.class)
    public void getInformeByIdTestError() throws RuntimeException{
        Mockito.when(reportRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_REPORT_EMPTY);
        informeServiceImpl.getReportById(INFORME_ID);
        fail();
    }


    @Test
    public void createInformeTest() throws RuntimeException{
        Mockito.when(tutoriaRepository.findById(TUTORIA_ID)).thenReturn(OPTIONAL_TUTORIA);
        Mockito.when(customerServiceClient.findStudentById(ALUMNO_ID)).thenReturn(OPTIONAL_ALUMNO);
        Mockito.when(reportRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_REPORT);
        Mockito.when(reportRepository.save(Mockito.any(Report.class))).thenReturn(REPORT);
        informeServiceImpl.createReport(CREATE_INFORME_DTO);
    }

    @Test(expected = MockitoException.class)
    public void createInformeInternalServerErrorTest() throws RuntimeException {
        Mockito.when(reportRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_REPORT);
        Mockito.doThrow(Exception.class).when(reportRepository).save(Mockito.any(Report.class));
        informeServiceImpl.createReport(CREATE_INFORME_DTO);
        fail();
    }

    @Test
    public void deleteInformeOk() throws RuntimeException {
        Mockito.when(reportRepository.findById(INFORME_ID)).thenReturn(Optional.of(REPORT));
        reportRepository.deleteById(INFORME_ID);
    }

    @Test(expected = RuntimeException.class)
    public void deleteInformeNotFountError() throws RuntimeException {
        Mockito.when(reportRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_REPORT_EMPTY);
        final String response = informeServiceImpl.deleteReport(INFORME_ID);
        assertEquals(response, INFORME_DELETED);
        fail();
    }

    @Test(expected = MockitoException.class)
    public void deleteInformeInternalServerError() throws RuntimeException {
        Mockito.when(reportRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_REPORT);
        Mockito.doThrow(Exception.class).when(reportRepository).deleteById(INFORME_ID);
        final String response = informeServiceImpl.deleteReport(INFORME_ID);
        assertEquals(response, INFORME_DELETED);
        fail();
    }
}
