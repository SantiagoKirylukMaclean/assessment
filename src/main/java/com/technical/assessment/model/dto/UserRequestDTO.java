package com.technical.assessment.model.dto;


import com.technical.assessment.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Data

public class UserRequestDTO {

    @Size(min = 1, max = 128)
    private String name;
    @Size(min = 1, max = 128)
    private String lastName;
    @Size(min = 1, max = 128)
    private String username;
    @Email(message = "*Please enter a valid Email.")
    private String email;
    @Size(min = 1, max = 128)
    private String password;
    private Set<String> roles;

}