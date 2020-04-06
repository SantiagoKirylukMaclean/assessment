package com.technical.assessment.model.dto;

import com.technical.assessment.model.Role;
import lombok.Data;

import java.util.Calendar;
import java.util.Set;

@Data
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastName;
    private Calendar modifyDateTime;
    private Long insuranceId;
    private Set<Role> roles;
}