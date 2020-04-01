package com.technical.assessment.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Set;

@Data
@Entity
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private Long id;

    @Column(name = "name")
    @Length(min = 1, message = "*Your name must have at least 1 characters")
    @NotEmpty(message = "*Please provide an name")
    private String name;

    @Column(name = "address")
    @Length(min = 5, message = "*Your name must have at least 5 characters")
    @NotEmpty(message = "*Please provide an address")
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDateTime;


}
