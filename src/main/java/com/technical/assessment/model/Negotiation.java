package com.technical.assessment.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@Entity
@Table(name = "negotiation")
public class Negotiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "negotiation_id")
    private Long id;

    @Column(name = "amount")
    @NotNull(message = "*Please provide your amount")
    private Double amount;

    @Column(name = "description_message")
    private String descriptionMessage;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDateTime;



}
