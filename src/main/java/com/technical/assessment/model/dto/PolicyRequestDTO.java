package com.technical.assessment.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class PolicyRequestDTO {

    @Size(min = 1, max = 128)
    private String policieDescription;
    @Size(min = 1, max = 128)
    private String coveredName;
    @Size(min = 1, max = 128)
    private String coveredLastName;
    @Email(message = "*Please enter a valid Email.")
    private String coveredEmail;
}
