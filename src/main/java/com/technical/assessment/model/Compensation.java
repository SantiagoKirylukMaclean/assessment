package com.technical.assessment.model;

import lombok.Data;

import java.util.Calendar;

@Data
public class Compensation {

    private Double compensationAmount;
    private Calendar modifyDateTime;
    private Long claimId;
}
