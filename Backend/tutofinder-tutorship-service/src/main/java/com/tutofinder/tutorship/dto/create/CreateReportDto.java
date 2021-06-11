package com.tutofinder.tutorship.dto.create;

import com.tutofinder.tutorship.dto.StudentDto;
import lombok.Data;

@Data
public class CreateReportDto {
    private String descriptionReport;
    private String status;
    private StudentDto student;
}
