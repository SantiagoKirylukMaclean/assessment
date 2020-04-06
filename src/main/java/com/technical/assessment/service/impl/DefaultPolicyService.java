package com.technical.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.assessment.model.Claim;
import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.Policy;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.PolicyRepository;
import com.technical.assessment.service.PolicyServiceInterface;
import com.technical.assessment.service.UserServiceInterface;
import com.technical.assessment.utils.TextMessages;
import com.technical.assessment.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
    private ObjectMapper objectMapper;

    public DefaultPolicyService(PolicyRepository policyRepository, InsuranceRepository insuranceRepository,
                                UserServiceInterface userServiceInterface, Utility utility
                                , ObjectMapper objectMapper) {
        this.policyRepository = policyRepository;
        this.insuranceRepository = insuranceRepository;
        this.userServiceInterface = userServiceInterface;
        this.utility = utility;

        this.objectMapper = objectMapper;
    }

    public List<Policy> getPoliciesByUsername(String username) {
        User user = userServiceInterface.getUserByUserName(username).orElse(new User());
        return policyRepository.findAll().stream()
                .filter(p -> p.getInsurance().getId().equals(user.getInsurance().getId()))
                .collect(Collectors.toList());
    }

    public Policy getPolicyByUsername(String username, String id) {
        User user = userServiceInterface.getUserByUserName(username).orElse(new User());
        return policyRepository.findAll().stream()
                .filter(p -> p.getInsurance().getId().equals(user.getInsurance().getId()))
                .filter(p -> p.getId().equals(Long.parseLong(id))).findFirst().orElse(new Policy());
    }

    public Policy savePolicy(Policy policy, String username) {
        Optional<User> user = userServiceInterface.getUserByUserName(username);
        policy.setInsurance(insuranceRepository.findById(user.orElse(new User()).getInsurance().getId())
                .orElse(new Insurance()));
        policy.setModifyDateTime(Calendar.getInstance());
        policyRepository.save(policy);
        return policy;
    }

    public Policy savePolicy(Map<String, Object> updates, String username, String policieId) {
        Optional<User> userHeader = userServiceInterface.getUserByUserName(username);
        Policy policy = policyRepository.findById(Long.parseLong(policieId)).orElse(new Policy());
        if (policy.getId() == null){
            log.debug(TextMessages.OBJECT_NOT_EXIST);
            return new Policy();
        }
        if (!userHeader.get().getInsurance().getId().equals(policy.getInsurance().getId())) {
            log.debug(TextMessages.LOGGED_USER_NOT_INSURANCE);
            return new Policy();
        }
        policy = objectMapper.convertValue(utility.modifyField(updates, policy), Policy.class);
        policy.setModifyDateTime(Calendar.getInstance());
        policyRepository.save(policy);
        return policy;
    }
}
