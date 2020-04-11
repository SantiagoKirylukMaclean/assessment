package com.technical.assessment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class InsuranceRequestDTO {

    @Size(min = 1, max = 128)
    private String name;
    @Size(min = 1, max = 128)
    private String address;
    @NonNull
    private Double automaticAcceptAmount;
}
