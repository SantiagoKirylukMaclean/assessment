package com.technical.assessment.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Set;

@Data
@Entity
@Table(name = "claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Long id;

    @Column(name = "state")
    @NotNull(message = "*Please provide your amount")
    private int state;

    @ManyToOne
    @JoinColumn(name = "guilty_policy_id", referencedColumnName = "policy_id")
    private Policy policyGuilty;

    @ManyToOne
    @JoinColumn(name = "victim_policy_id", referencedColumnName = "policy_id")
    private Policy policyVictim;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Negotiation> negotiations;

    @Column(name = "amount")
    @NotNull(message = "*Please provide your amount")
    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDateTime;

}
