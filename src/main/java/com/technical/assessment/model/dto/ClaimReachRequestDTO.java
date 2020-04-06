package com.technical.assessment.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ClaimReachRequestDTO {

    @Size(min = 1, max = 128)
    private String descriptionMessage;
}
