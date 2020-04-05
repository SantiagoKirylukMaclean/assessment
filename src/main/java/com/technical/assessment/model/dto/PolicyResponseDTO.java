package com.technical.assessment.model.dto;

import com.technical.assessment.model.Insurance;
import lombok.Data;

import java.util.Calendar;

@Data
public class PolicyResponseDTO {

    private Long id;
    private String policieDescription;
    private String coveredName;
    private String coveredLastName;
    private String coveredEmail;
    private Calendar modifyDateTime;
    private Long insuranceId;
}
