package com.tutofinder.tutorship.dto.create;

import lombok.Data;

@Data
public class CreateReportDto {
    private String descriptionReport;
    private Long studentId;
    private Long tutorShipId;
}
