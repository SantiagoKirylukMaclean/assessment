package com.technical.assessment.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Calendar;

@Data
@Entity
@Table(name = "policy")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id")
    private Long id;

    @Size(max = 128)
    @Column(name = "policy_description")
    @NotEmpty(message = "*Please provide an policie")
    private String policieDescription;

    @Size(max = 128)
    @Column(name = "covered_name")
    @NotEmpty(message = "*Please provide your name")
    private String coveredName;

    @Size(max = 128)
    @Column(name = "covered_last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String coveredLastName;

    @Column(name = "covered_email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide your email")
    private String coveredEmail;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDateTime;

    @ManyToOne
    @JoinColumn(name = "insurance_id", referencedColumnName = "insurance_id")
    private Insurance insurance;
}
