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
