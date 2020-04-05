package com.technical.assessment.service;

import com.technical.assessment.model.Insurance;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface InsuranceServiceInterface {

    Insurance getInsuranceByUserName(String username);
    Insurance saveInsurance(Map<String, Object> updates, String id);
}
