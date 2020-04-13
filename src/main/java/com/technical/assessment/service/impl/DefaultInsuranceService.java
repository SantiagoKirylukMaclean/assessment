package com.technical.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.service.InsuranceServiceInterface;
import com.technical.assessment.service.UserServiceInterface;
import com.technical.assessment.utils.TextMessages;
import com.technical.assessment.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class DefaultInsuranceService implements InsuranceServiceInterface {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private Utility utility;

    @Autowired
    private ObjectMapper objectMapper;

    public DefaultInsuranceService(InsuranceRepository insuranceRepository,
                                   UserServiceInterface userServiceInterface, Utility utility,
                                   ObjectMapper objectMapper) {
        this.insuranceRepository = insuranceRepository;
        this.userServiceInterface = userServiceInterface;
        this.utility = utility;
        this.objectMapper = objectMapper;
    }

    public Insurance getInsuranceByUserName(String username) {
        Optional<User> userOptional = userServiceInterface.getUserByUserName(username);
        Insurance insurance = new Insurance();
        if(userOptional.isPresent()) {
            insurance = insuranceRepository.findAll().stream()
                    .filter(i -> i.getId().equals(userOptional.get().getInsurance().getId()))
                    .findFirst().orElse(new Insurance());
        }
        return insurance;
    }

    public Insurance updateFieldsInsurance(Map<String, Object> updates, String username) {
        Optional<User> userOptional = userServiceInterface.getUserByUserName(username);
        Insurance insurance = new Insurance();
        if(userOptional.isPresent()) {
            insurance = insuranceRepository.
                    findById(Long.parseLong(userOptional.get().getInsurance().getId().toString()))
                    .orElse(new Insurance());
            insurance = objectMapper.convertValue(utility.modifyField(updates, insurance), Insurance.class);
            insurance.setModifyDateTime(Calendar.getInstance());
            insuranceRepository.save(insurance);
        }
        return insurance;
    }

    public Insurance updateInsurance(Insurance insuranceUpdate, String username) {
        Optional<User> userHeader = userServiceInterface.getUserByUserName(username);
        Insurance insurance = insuranceRepository.findById(userHeader.orElse(new User()).getInsurance().getId())
                .orElse(new Insurance());
        if (insurance.getId() == null){
            log.debug(TextMessages.REQUEST_DATA_NOT_AVAILABLE);
            return new Insurance();
        }
        if (!userHeader.get().getInsurance().getId().equals(insurance.getId())) {
            log.debug(TextMessages.LOGGED_USER_NOT_INSURANCE);
            return new Insurance();
        }
        insuranceUpdate.setId(insurance.getId());
        insuranceUpdate.setModifyDateTime(Calendar.getInstance());
        return insuranceRepository.save(insuranceUpdate);
    }
}
