package com.technical.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.assessment.model.Policy;
import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.PolicyRequestDTO;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.PolicyRepository;
import com.technical.assessment.service.PolicyServiceInterface;
import com.technical.assessment.service.UserServiceInterface;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Policy> getPoliciesByUsername(String username) {
        User user = userServiceInterface.getUserByUserName(username).get();
        List<Policy> policies = policyRepository.findAll().stream()
                .filter(p -> p.getInsurance().getId().equals(user.getInsurance().getId()))
                .collect(Collectors.toList());
        return policies;
    }

    public Policy getPolicyByUsername(String username, String id) {

        User user = userServiceInterface.getUserByUserName(username).get();
        Policy policy = policyRepository.findAll().stream()
                .filter(p -> p.getInsurance().getId().equals(user.getInsurance().getId()))
                .filter(p -> p.getId().equals(Long.parseLong(id))).findFirst().get();
        return policy;
    }

    public Policy savePolicy(Policy policy, String username) {
        Optional<User> user = userServiceInterface.getUserByUserName(username);
        policy.setInsurance(insuranceRepository.findById(user.get().getInsurance().getId()).get());
        policy.setModifyDateTime(Calendar.getInstance());
        policyRepository.save(policy);
        return policy;
    }

    public Policy savePolicy(Map<String, Object> updates, String username, String policieId) {
        Policy policy = policyRepository.findById(Long.parseLong(policieId)).get();
        ObjectMapper oMapper = new ObjectMapper();
        policy = oMapper.convertValue(utility.modifyField(updates, policy), Policy.class);
        policy.setModifyDateTime(Calendar.getInstance());
        policyRepository.save(policy);
        return policy;
    }


}
