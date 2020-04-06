package com.technical.assessment.service.impl;

import com.technical.assessment.model.Claim;
import com.technical.assessment.model.Compensation;
import com.technical.assessment.model.Negotiation;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.ClaimRepository;
import com.technical.assessment.repository.UserRepository;
import com.technical.assessment.service.CompensationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultCompensationService implements CompensationServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClaimRepository claimRepository;

    public DefaultCompensationService(UserRepository userRepository, ClaimRepository claimRepository) {
        this.userRepository = userRepository;
        this.claimRepository = claimRepository;
    }

    public List<Compensation> getCompensationsByUserName(String headerUsername) {
        List<Compensation> compensations = new ArrayList<>();
        Optional<User> headerUser = userRepository.findByUsername(headerUsername);
        List<Claim> closedClimes = claimRepository.findAll().stream()
                .filter(claim -> claim.getPolicyVictim().getInsurance().getId().equals(headerUser.orElse(new User()).getInsurance().getId()))
                .filter(claim -> claim.getState() == 3).collect(Collectors.toList());
        if (closedClimes.size() > 0) {

            for (Claim claim : closedClimes) {
                //TODO: Review asignation.
                Compensation compensation = new Compensation();
                compensation.setCompensationAmount(claim.getNegotiations().stream()
                        .max(Comparator.comparing(Negotiation::getId)).orElse(new Negotiation()).getAmount());
                compensation.setModifyDateTime(claim.getModifyDateTime());
                compensation.setClaimId(claim.getId());
                compensations.add(compensation);
            }
        }
        return compensations;
    }
}
