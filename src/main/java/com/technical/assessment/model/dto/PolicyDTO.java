package com.technical.assessment.model.dto;

import com.technical.assessment.model.Insurance;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data

public class PolicyDTO {

    private String policieDescription;

    private String coveredName;

    private String coveredLastName;

    private String coveredEmail;

}
