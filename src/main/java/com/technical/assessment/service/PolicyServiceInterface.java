package com.technical.assessment.service;

import com.technical.assessment.model.Policy;
import com.technical.assessment.model.dto.PolicyRequestDTO;
import org.omg.PortableServer.POA;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PolicyServiceInterface {

    List<Policy> getPoliciesByUsername(String username);
    Policy getPolicyByUsername(String username, String id);
    Policy savePolicy(Policy policy, String username);
    Policy savePolicy(Map<String, Object> updates, String username, String policieId);
}