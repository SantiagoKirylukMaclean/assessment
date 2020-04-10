package com.technical.assessment.service;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.dto.InsuranceRequestDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface InsuranceServiceInterface {

    Insurance getInsuranceByUserName(String username);
    Insurance updateFieldsInsurance(Map<String, Object> updates, String id);
    Insurance updateInsurance(Insurance insuranceRequestDTO, String username);
}
