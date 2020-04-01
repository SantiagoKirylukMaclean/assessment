package com.technical.assessment.model.dto;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Length(min = 4, message = "*Your username must have at least 4 characters")
    @NotEmpty(message = "*Please provide an username")
    private String username;


    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;


    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;


    @NotEmpty(message = "*Please provide your name")
    private String name;


    @NotEmpty(message = "*Please provide your last name")
    private String lastName;


    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDateTime;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}