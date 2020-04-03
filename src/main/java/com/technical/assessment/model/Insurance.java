package com.technical.assessment.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@Entity
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private Long id;

    @Column(name = "name")
    @Length(min = 1, message = "*Your Name must have at least 1 characters")
    @NotEmpty(message = "*Please provide an name")
    private String name;

    @Column(name = "address")
    @Length(min = 5, message = "*Your Address must have at least 5 characters")

    @NotEmpty(message = "*Please provide an address")
    private String address;

    @NotNull(message = "*Please provide your automatic amount")
    private Double automaticAcceptAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDateTime;

}
