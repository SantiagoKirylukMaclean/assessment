package com.technical.assessment.model.dto;

import lombok.Data;

@Data
public class ClaimRequestDTO {

    private Long policyGuilty;
    private Long policyVictim;
    private Double amount;

}
