package com.technical.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.assessment.model.Policy;
import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.PolicyDTO;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.PolicyRepository;
import com.technical.assessment.service.PolicyServiceInterface;
import com.technical.assessment.service.UserServiceInterface;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultPolicyService implements PolicyServiceInterface {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private Utility utility;

    @Autowired
    ModelMapper modelMapper;



    public ResponseEntity<List<Policy>> getPolicyByUsername(String username) {

        try {
            Optional<User> user = userServiceInterface.getUserByUserName(username);
            List<Policy> policy = policyRepository.findAll().stream()
                    .filter(p -> p.getInsurance().getId().equals(user.get().getInsurance().getId()))
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(policy);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    public ResponseEntity<Policy> getPolicyByUsername(String username, String id){
        Optional<Policy> policy = null;
        try {
            Optional<User> user = userServiceInterface.getUserByUserName(username);
            policy = policyRepository.findAll().stream()
                    .filter(p -> p.getInsurance().getId().equals(user.get().getInsurance().getId()))
                    .filter(p -> p.getId().equals(Long.parseLong(id))).findFirst();

                return ResponseEntity.status(HttpStatus.OK).body(policy.get());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    public ResponseEntity<?> savePolicy(PolicyDTO policyDTO, String username){

        Policy policy = modelMapper.map(policyDTO, Policy.class);
        Optional<User> user = userServiceInterface.getUserByUserName(username);
        policy.setInsurance(insuranceRepository.findById(user.get().getInsurance().getId()).get());
        try {
            policyRepository.save(policy);
            return ResponseEntity.status(HttpStatus.OK).body("policy saved ok");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> savePolicy(Map<String, Object> updates, String username, String policieId) {
        Optional<User> user = userServiceInterface.getUserByUserName(username);
        //Utility utility = Utility.getInstance();
        try {
            Policy policy = policyRepository.findById(Long.parseLong(policieId)).get();

            ObjectMapper oMapper = new ObjectMapper();
            policy = oMapper.convertValue(utility.modifyField(updates, policy), Policy.class);
            policy.setModifyDateTime(Calendar.getInstance());
            policyRepository.save(policy);

            return ResponseEntity.status(HttpStatus.CREATED).body("Policie has updated");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insurance do not exist - " + e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }


}
