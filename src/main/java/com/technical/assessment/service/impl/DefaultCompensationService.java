package com.technical.assessment.service.impl;

import com.technical.assessment.model.Claim;
import com.technical.assessment.model.dto.CompensationResponseDTO;
import com.technical.assessment.model.Negotiation;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.ClaimRepository;
import com.technical.assessment.repository.UserRepository;
import com.technical.assessment.service.CompensationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultCompensationService implements CompensationServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClaimRepository claimRepository;



    public ResponseEntity<List<CompensationResponseDTO>> getCompensationsByUserName(String headerUsername) {
        try {
            Optional<User> headerUser = userRepository.findByUsername(headerUsername);
            List<Claim> closedClimes = claimRepository.findAll().stream()
                    .filter(claim -> claim.getPolicyVictim().getInsurance().getId().equals(headerUser.get().getInsurance().getId()))
                    .filter(claim -> claim.getState() == 3).collect(Collectors.toList());
            if(closedClimes.size() == 0){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            List<CompensationResponseDTO> compensations = new ArrayList<>();
            CompensationResponseDTO compensationResponseDTO = new CompensationResponseDTO();
            for (Claim claim:closedClimes) {
                compensationResponseDTO.setCompensationAmount(claim.getNegotiations().stream().max(Comparator.comparing(Negotiation::getId)).get().getAmount());
                compensationResponseDTO.setModifyDateTime(claim.getModifyDateTime());
                compensationResponseDTO.setClaimId(claim.getId());
                compensations.add(compensationResponseDTO);
            }

            return ResponseEntity.status(HttpStatus.OK).body(compensations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
