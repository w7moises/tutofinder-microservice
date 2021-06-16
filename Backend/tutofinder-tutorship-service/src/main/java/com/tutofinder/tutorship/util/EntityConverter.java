package com.tutofinder.tutorship.util;

import java.util.List;
import java.util.stream.Collectors;

import com.tutofinder.tutorship.dto.CourseDto;
import com.tutofinder.tutorship.dto.ReportDto;
import com.tutofinder.tutorship.dto.TutorShipDto;
import com.tutofinder.tutorship.dto.create.CreateCourseDto;
import com.tutofinder.tutorship.dto.create.CreateReportDto;
import com.tutofinder.tutorship.dto.create.CreateTutorShipDto;
import com.tutofinder.tutorship.entities.Course;
import com.tutofinder.tutorship.entities.Report;
import com.tutofinder.tutorship.entities.TutorShip;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CourseDto convertCourseToDto(Course course) {
        return modelMapper.map(course, CourseDto.class);
    }

    public List<CourseDto> convertCourseToDto(List<Course> courses) {
        return courses.stream().map(course -> modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());
    }

    public Course convertCourseToEntity(CourseDto courseDto) {
        return modelMapper.map(courseDto, Course.class);
    }

    public Course convertCreateCourseToEntity(CreateCourseDto courseDto) {
        return modelMapper.map(courseDto, Course.class);
    }



    public ReportDto convertReportToDto(Report report) {
        return modelMapper.map(report, ReportDto.class);
    }

    public List<ReportDto> convertReportToDto(List<Report> reports) {
        return reports.stream().map(report -> modelMapper.map(report, ReportDto.class)).collect(Collectors.toList());
    }

    public Report convertReportToEntity(ReportDto reportDto) {
        return modelMapper.map(reportDto, Report.class);
    }

    public Report convertCreateReportToEntity(CreateReportDto reportDto) {
        return modelMapper.map(reportDto, Report.class);
    }

    

    public TutorShipDto convertTutorShipToDto(TutorShip tutorShip) {
        return modelMapper.map(tutorShip, TutorShipDto.class);
    }

    public List<TutorShipDto> convertTutorShipToDto(List<TutorShip> tutorShips) {
        return tutorShips.stream().map(tutorShip -> modelMapper.map(tutorShip, TutorShipDto.class))
                .collect(Collectors.toList());
    }

    public TutorShip convertTutorShipToEntity(TutorShipDto tutorShipDto) {
        return modelMapper.map(tutorShipDto, TutorShip.class);
    }

    public TutorShip convertCreateTutorShipToEntity(CreateTutorShipDto tutorShipDto) {
        return modelMapper.map(tutorShipDto, TutorShip.class);
    }
}
