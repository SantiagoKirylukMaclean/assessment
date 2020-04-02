package com.technical.assessment.model.dto;


import com.technical.assessment.model.Role;
import lombok.Data;

import java.util.Calendar;
import java.util.Set;

@Data

public class UserDTO {

    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;

}