package com.technical.assessment.service;

import com.technical.assessment.model.Policy;
import com.technical.assessment.model.dto.PolicyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface PolicyServiceInterface {


    ResponseEntity<List<Policy>> getPolicyByUsername(String username);
    ResponseEntity<Policy> getPolicyByUsername(String username, String id);
    ResponseEntity<?> savePolicy(PolicyDTO policyDTO, String username);
    ResponseEntity<?> savePolicy(Map<String, Object> updates, String username, String policieId);
}
