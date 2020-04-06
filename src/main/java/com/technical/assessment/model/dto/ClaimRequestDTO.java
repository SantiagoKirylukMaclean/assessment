package com.technical.assessment.model.dto;

import com.technical.assessment.model.Policy;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ClaimRequestDTO {

    @NotNull(message = "*Please provide your amount")
    private Long policyGuilty;
    @NotNull(message = "*Please provide your amount")
    private Long policyVictim;
    @NotNull(message = "*Please provide your amount")
    private Double amount;

}
