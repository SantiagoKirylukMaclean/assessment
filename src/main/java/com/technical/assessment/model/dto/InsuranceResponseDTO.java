package com.technical.assessment.model.dto;

import lombok.Data;
import java.util.Calendar;

@Data
public class InsuranceResponseDTO {

    private Long id;
    private String name;
    private String address;
    private Double automaticAcceptAmount;
    private Calendar modifyDateTime;
}
