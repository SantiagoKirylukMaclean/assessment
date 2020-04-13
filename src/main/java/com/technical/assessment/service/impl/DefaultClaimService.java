package com.technical.assessment.service.impl;

import com.technical.assessment.model.Claim;
import com.technical.assessment.model.Negotiation;
import com.technical.assessment.model.Policy;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.ClaimRepository;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.PolicyRepository;
import com.technical.assessment.repository.RoleRepository;
import com.technical.assessment.repository.UserRepository;
import com.technical.assessment.service.ClaimServiceInterface;
import com.technical.assessment.utils.TextMessages;
import com.technical.assessment.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.table.TableRowSorter;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultClaimService implements ClaimServiceInterface {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ClaimRepository claimRepository;

    @Autowired
    private final PolicyRepository policyRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final InsuranceRepository insuranceRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final Utility utility;

    public DefaultClaimService(UserRepository userRepository, ClaimRepository claimRepository,
                               PolicyRepository policyRepository, ModelMapper modelMapper,
                               InsuranceRepository insuranceRepository, RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder, Utility utility) {
        this.userRepository = userRepository;
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
        this.modelMapper = modelMapper;
        this.insuranceRepository = insuranceRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.utility = utility;
    }

    public Claim getClaimsById(String headerUsername, String claimId) {
        return claimRepository.findById(Long.parseLong(claimId)).orElseThrow(NoSuchElementException::new);
    }

    public List<Claim> getClaimsGuiltyByUserName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return claimRepository.findAll().stream()
                .filter(cg -> cg.getPolicyGuilty().getInsurance().getId()
                        .equals(user.orElseThrow(NoSuchElementException::new).getInsurance().getId())).collect(Collectors.toList());
    }

    public List<Claim> getClaimsVictimByUserName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return claimRepository.findAll().stream()
                .filter(cg -> cg.getPolicyVictim().getInsurance().getId()
                        .equals(user.orElseThrow(NoSuchElementException::new).getInsurance().getId()))
                .collect(Collectors.toList());
    }

    public Claim addClaim(Claim claim, String username) {
        if (claim.getPolicyGuilty().getId().equals(claim.getPolicyVictim().getId())) {
            log.debug(TextMessages.POLICIES_ARE_SAME);
            //return new Claim();
            throw new NoSuchElementException();
        }
        User userHeader = userRepository.findByUsername(username).orElse(new User());
        claim.setPolicyVictim(policyRepository.findById(claim.getPolicyVictim().getId()).orElse(new Policy()));
        claim.setPolicyGuilty(policyRepository.findById(claim.getPolicyGuilty().getId()).orElse(new Policy()));

        List<Policy> policies = policyRepository.findAll();
        Policy policyGuilty = policies.stream()
                .filter(policy -> claim.getPolicyGuilty().getId().equals(policy.getId()))
                .findAny().orElse(null);
        Policy policyVictim = policies.stream()
                .filter(policy -> claim.getPolicyVictim().getId().equals(policy.getId()))
                .findAny().orElse(null);

        //Check if insurace of user is the same of policyVictim
        if (!userHeader.getInsurance().getId().equals(policyVictim.getInsurance().getId())) {
            log.debug(TextMessages.LOGGED_USER_NOT_INSURANCE);
            return new Claim();
        }

        //Check is booth insuranse as same
        if (policyGuilty.getInsurance().getId().equals(policyVictim.getInsurance().getId())) {
            setClaim(policyGuilty, policyVictim, claim, 3);
            setNegotiation(claim, TextMessages.SAME_VICTIM_AND_INSURANCE, claim.getAmount());
        } else if (Double.compare(claim.getAmount(), policyGuilty.getInsurance().getAutomaticAcceptAmount()) <= 0) {
            setClaim(policyGuilty, policyVictim, claim, 3);
            setNegotiation(claim, TextMessages.PROPOSAL_LESS_AUTOMATIC, claim.getAmount());
        } else {
            setClaim(policyGuilty, policyVictim, claim, 1);
            setNegotiation(claim, TextMessages.CLAIM_STARTED, claim.getAmount());
        }
        claimRepository.save(claim);
        return claim;
    }

    public Claim rejectClaim(String claimId, Negotiation negotiation, String username) {
        Optional<User> userHeader = userRepository.findByUsername(username);
        Claim claim = claimRepository.findById(Long.parseLong(claimId)).orElse(new Claim());

        if (claim.getState() != 1) {
            log.debug(TextMessages.CLAIM_STATE_NOT_OFFERED);
            return new Claim();
        }

        //Check if insurace of user is the same od policyGuilty
        if (!userHeader.orElse(new User()).getInsurance().getId().equals(claim.getPolicyGuilty().getInsurance().getId())) {
            log.debug(TextMessages.LOGGED_USER_NOT_INSURANCE);
            return new Claim();
        }
        Double lasAmountProposal = getlastAmountProposal(claim);
        updateClaim(claim, 2);
        setNegotiation(claim, negotiation.getDescriptionMessage(), lasAmountProposal);

        claimRepository.save(claim);
        return claim;
    }

    public Claim reachClaim(String claimId, Negotiation negotiation, String username) {
        Optional<User> userHeader = userRepository.findByUsername(username);
        Claim claim = claimRepository.findById(Long.parseLong(claimId)).orElse(new Claim());

        //Check if state is offered
        if (claim.getState() != 1) {
            log.debug(TextMessages.CLAIM_STATE_NOT_OFFERED);
            return new Claim();
        }

        //Check if insurace of user is the same od policyGuilty
        if (!userHeader.get().getInsurance().getId().equals(claim.getPolicyGuilty().getInsurance().getId())) {
            log.debug(TextMessages.LOGGED_USER_NOT_INSURANCE);
            return new Claim();
        }
        Double lastAmountProposal = getlastAmountProposal(claim);
        updateClaim(claim, 3);
        setNegotiation(claim, negotiation.getDescriptionMessage(), lastAmountProposal);

        claimRepository.save(claim);
        return claim;
    }

    public Claim proposalClaim(String claimId, Negotiation negotiation, String username) {
        Optional<User> userHeader = userRepository.findByUsername(username);
        Claim claim = claimRepository.findById(Long.parseLong(claimId)).orElse(new Claim());

        //Check if state is offered
        if (claim.getState() != 2) {
            log.debug(TextMessages.CLAIM_STATE_NOT_OFFERED);
            return new Claim();
        }
        //Check is loggerd user have same insurace than policy victim
        if (!userHeader.get().getInsurance().getId().equals(claim.getPolicyVictim().getInsurance().getId())) {
            log.debug(TextMessages.LOGGED_USER_NOT_INSURANCE);
            return new Claim();
        }
        Double lasAmountProposal = getlastAmountProposal(claim);
        //Check is proposal amount is beyond las proposal amount
        if (Double.compare(negotiation.getAmount(), lasAmountProposal) >= 0) {
            log.debug(TextMessages.PROPOSAL_AMOUNT_LOWER);
            return new Claim();
        }
        updateClaim(claim, 1);
        setNegotiation(claim, negotiation.getDescriptionMessage(), negotiation.getAmount());

        claimRepository.save(claim);
        return claim;
    }

    private Double getlastAmountProposal(Claim claim) {
        return claim.getNegotiations().stream().max(Comparator.comparing(Negotiation::getId)).orElse(new Negotiation()).getAmount();
    }

    private void setClaim(Policy policyGuilty, Policy policyVictim, Claim claim, int status) {
        claim.setPolicyGuilty(policyGuilty);
        claim.setPolicyVictim(policyVictim);
        claim.setState(status);
        claim.setModifyDateTime(Calendar.getInstance());
    }

    private void updateClaim(Claim claim, int status) {
        claim.setState(status);
        claim.setModifyDateTime(Calendar.getInstance());
    }

    private void setNegotiation(Claim claim, String descritionMessage, Double negotiationAmount) {
        Set<Negotiation> negotiations = new HashSet<>();
        if (claim.getNegotiations() != null) {
            negotiations.addAll(claim.getNegotiations());
        }
        Negotiation negotiation = new Negotiation();
        negotiation.setAmount(negotiationAmount);
        negotiation.setDescriptionMessage(descritionMessage);
        negotiation.setModifyDateTime(Calendar.getInstance());
        negotiations.add(negotiation);
        claim.setNegotiations(negotiations);
    }
}
