package com.technical.assessment.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "policie")
public class Policie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policie_id")
    private Long id;

    @Column(name = "policie_description")
    @Length(min = 5, message = "*Your name must have at least 5 characters")
    @NotEmpty(message = "*Please provide an policie")
    private String policieDescription;

    @Column(name = "covered_name")
    @NotEmpty(message = "*Please provide your name")
        private String coveredName;

    @Column(name = "covered_last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String coveredLastName;

    @Column(name = "covered_email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide your email")
    private String coveredLastEmail;
}
