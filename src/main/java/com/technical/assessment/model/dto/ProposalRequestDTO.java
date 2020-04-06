package com.technical.assessment.model.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
public class ProposalRequestDTO {

    @NonNull
    private Double amount;
    @Size(min = 1, max = 128)
    private String descriptionMessage;
}
