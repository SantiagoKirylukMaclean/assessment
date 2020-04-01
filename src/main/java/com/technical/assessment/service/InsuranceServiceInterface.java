package com.technical.assessment.service;

import com.technical.assessment.model.Insurance;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface InsuranceServiceInterface {

    List<Insurance> getAllInsurance();
    ResponseEntity<Insurance> getInsuranceByUserName(String username);
    ResponseEntity<?> saveInsurance(Map<String, Object> updates, String id);
}
