package com.technical.assessment.model.dto;

import lombok.Data;

import java.util.Calendar;

@Data
public class CompensationResponseDTO {

    private Double compensationAmount;
    private Calendar modifyDateTime;
    private Long claimId;
}
