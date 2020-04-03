package com.technical.assessment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.light.SpotLight;
import com.technical.assessment.model.*;
import com.technical.assessment.model.dto.ClaimRequestDTO;
import com.technical.assessment.model.dto.RejectRequestDTO;
import com.technical.assessment.model.dto.UserDTO;
import com.technical.assessment.repository.*;
import com.technical.assessment.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class DefaultClaimService implements ClaimServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    PolicyRepository policyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Utility utility;


    public ResponseEntity<List<Claim>> getClaimsByUserName(String username) {
        try {

            Optional<User> user = userRepository.findByUsername(username);
            List<Claim> claims = claimRepository.findAll();
            List<Claim> claims1 = claimRepository.findAll().stream()
                    .filter(cg -> cg.getPolicyGuilty().getInsurance().getId().equals(user.get().getInsurance().getId())).
                            collect(Collectors.toList());
            List<Claim> claims2 = claimRepository.findAll().stream()
                    .filter(cg -> cg.getPolicyVictim().getInsurance().getId().equals(user.get().getInsurance().getId())).
                            collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).
                    body(Stream.concat(claims1.stream(), claims2.stream()).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    public ResponseEntity<List<Claim>> getClaimsGultyByUserName(String username) {
        try {
            Optional<User> user = userRepository.findByUsername(username);
            List<Claim> claims = claimRepository.findAll().stream()
                    .filter(cg -> cg.getPolicyGuilty().getInsurance().getId().equals(user.get().getInsurance().getId())).
                            collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).
                    body(claims);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    public ResponseEntity<List<Claim>> getClaimsVictimByUserName(String username) {
        try {
            Optional<User> user = userRepository.findByUsername(username);
            List<Claim> claims = claimRepository.findAll().stream()
                    .filter(cg -> cg.getPolicyVictim().getInsurance().getId().equals(user.get().getInsurance().getId())).
                            collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).
                    body(claims);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    public ResponseEntity<?> addClaim(ClaimRequestDTO claimRequestDTO, String username){

        try {
            User userHeader = userRepository.findByUsername(username).get();

            List<Policy> policies = policyRepository.findAll();
            Policy policyGuilty = policies.stream()
                    .filter(policy -> claimRequestDTO.getPolicyGuilty().equals(policy.getId()))
                    .findAny().orElse(null);
            Policy policyVictim = policies.stream()
                    .filter(policy -> claimRequestDTO.getPolicyVictim().equals(policy.getId()))
                    .findAny().orElse(null);

            if(userHeader.getInsurance().getId().equals(policyVictim.getInsurance().getId())){
                log.debug("Insurance of header user is the same of insurance victim policy");
                Claim claim = modelMapper.map(claimRequestDTO, Claim.class);
               if(policyGuilty.getInsurance().getId().equals(policyVictim.getInsurance().getId())){
                   log.info("Mismos Insurances");
                   setClaim(policyGuilty, policyVictim, claim, 3);
                   setNegotiation(claim, "Same victim and guilty insurance, close automatic");
               }else if(Double.compare(claim.getAmount(),policyGuilty.getInsurance().getAutomaticAcceptAmount()) <= 0) {
                   log.info("monto ofertado es menor al automatico");
                   setClaim(policyGuilty, policyVictim, claim, 3);
                   setNegotiation(claim, "Amount offered is less than automatic accept offer, close automatic");
               }else{
                   log.info("Inicio Tansaccion normal");
                   setClaim(policyGuilty, policyVictim, claim, 1);
                   setNegotiation(claim, "Init claim");
               }

                claimRepository.save(claim);
                return ResponseEntity.status(HttpStatus.OK).body("Claim saved ok");
            }else {
                log.debug("Insurance of header user is different than insurance victim policy");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatus.BAD_REQUEST.getReasonPhrase());
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> rejectClaim(String claimId, RejectRequestDTO rejectRequestDTO, String username) {
        try {
            Optional<User> userHeader = userRepository.findByUsername(username);;
            Optional<Claim> claim = claimRepository.findById(Long.parseLong(claimId));
            if (userHeader.get().getInsurance().getId().equals(claim.get().getPolicyGuilty().getInsurance().getId())){
                log.info("el insurance del usuario logueado es igual al insurance de la poliza culpable");
                updateClaim(claim.get(),2);
                setNegotiation(claim.get(), rejectRequestDTO.getDescriptionMessage());
            } else {
                log.info("Insurance of header user is different than insurance victim policy");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatus.BAD_REQUEST.getReasonPhrase());
            }

            claimRepository.save(claim.get());
            return ResponseEntity.status(HttpStatus.OK).body("Claim saved ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }



    private void setClaim(Policy policyGuilty, Policy policyVictim, Claim claim, int status){
        claim.setPolicyGuilty(policyGuilty);
        claim.setPolicyVictim(policyVictim);
        claim.setState(status);
        claim.setModifyDateTime(Calendar.getInstance());
    }

    private void updateClaim(Claim claim, int status){
        claim.setState(status);
        claim.setModifyDateTime(Calendar.getInstance());
    }

    private void setNegotiation(Claim claim, String descritionMessage){
        Set<Negotiation> negotiations = new HashSet<>();
        negotiations.addAll(claim.getNegotiations());
        Negotiation negotiation = new Negotiation();
        negotiation.setAmount(claim.getAmount());
        negotiation.setDescriptionMessage(descritionMessage);
        negotiation.setModifyDateTime(Calendar.getInstance());
        negotiations.add(negotiation);
        claim.setNegotiations(negotiations);
    }

    /*


    public ResponseEntity<?> addUser(UserDTO userDTO, String username){
        User userHeader = userRepository.findByUsername(username).get();

        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setInsurance(insuranceRepository.findById(userHeader.getInsurance().getId()).get());
        user.setActive(1);
        user.setModifyDateTime(Calendar.getInstance());
        List<Role> roles = new ArrayList<>();
        for (String role : userDTO.getRoles()){
            roles.add(roleRepository.findByRoleName(role));
        }

        user.setRoles(new HashSet<Role>(roles));

        try {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("user saved ok");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> saveUser(Map<String, Object> updates, String id){
        //Utility utility = Utility.getInstance();
        try {
            User user = userRepository.findById(Long.parseLong(id)).get();

            ObjectMapper oMapper = new ObjectMapper();
            user = oMapper.convertValue(utility.modifyField(updates,user), User.class);
            user.setModifyDateTime(Calendar.getInstance());

            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("User saved ok");
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insurance not found - " + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public Optional<User> getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
  */
}
