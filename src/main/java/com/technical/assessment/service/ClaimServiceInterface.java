package com.technical.assessment.service;

import com.technical.assessment.error.CustomAssessmentException;
import com.technical.assessment.model.Claim;
import com.technical.assessment.model.Negotiation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClaimServiceInterface {

    Claim getClaimsById(String headerUsername, String claimId);
    List<Claim> getClaimsGuiltyByUserName(String username);
    List<Claim> getClaimsVictimByUserName(String username);
    Claim addClaim(Claim claim, String username) throws CustomAssessmentException;
    Claim rejectClaim(String claimId, Negotiation negotiation, String username) throws CustomAssessmentException;
    Claim proposalClaim(String claimId, Negotiation negotiation, String username);
    Claim reachClaim(String claimId, Negotiation negotiation, String username) throws CustomAssessmentException;
}
