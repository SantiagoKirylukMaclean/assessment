package com.technical.assessment.model.dto;

import com.technical.assessment.model.Negotiation;
import lombok.Data;

import java.util.Calendar;
import java.util.Set;

@Data
public class ClaimResponseDTO {

    private Long id;
    private int state;
    private Long policyGuiltyId;
    private Long policyVictimId;
    private Set<Negotiation> negotiations;
    private Calendar modifyDateTime;
}
