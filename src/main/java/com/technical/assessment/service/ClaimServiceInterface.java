package com.technical.assessment.service;

import com.technical.assessment.model.Claim;
import com.technical.assessment.model.dto.ClaimRequestDTO;
import com.technical.assessment.model.dto.OfferRequestDTO;
import com.technical.assessment.model.dto.RejectRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClaimServiceInterface {

    ResponseEntity<List<Claim>> getClaimsByUserName(String username);
    ResponseEntity<Claim> getClaimsById(String headerUsername, String claimId);
    ResponseEntity<List<Claim>> getClaimsGultyByUserName(String username);
    ResponseEntity<List<Claim>> getClaimsVictimByUserName(String username);
    ResponseEntity<?> addClaim(ClaimRequestDTO claimRequestDTO, String username);
    ResponseEntity<?> rejectClaim(String claimId, RejectRequestDTO rejectRequestDTO, String username);
    ResponseEntity<?> proposalClaim(String claimId, OfferRequestDTO offerRequestDTO, String username);
    ResponseEntity<?> reachClaim(String claimId, RejectRequestDTO rejectRequestDTO, String username);
}
