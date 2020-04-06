package com.technical.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.service.InsuranceServiceInterface;
import com.technical.assessment.service.UserServiceInterface;
import com.technical.assessment.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;
import java.util.Optional;


@Service
public class DefaultInsuranceService implements InsuranceServiceInterface {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private Utility utility;

    public DefaultInsuranceService(InsuranceRepository insuranceRepository, UserServiceInterface userServiceInterface, Utility utility) {
        this.insuranceRepository = insuranceRepository;
        this.userServiceInterface = userServiceInterface;
        this.utility = utility;
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

    public Insurance saveInsurance(Map<String, Object> updates, String username) {
        Optional<User> userOptional = userServiceInterface.getUserByUserName(username);
        Insurance insurance = new Insurance();
        if(userOptional.isPresent()) {
            insurance = insuranceRepository.
                    findById(Long.parseLong(userOptional.get().getInsurance().getId().toString()))
                    .orElse(new Insurance());
            ObjectMapper oMapper = new ObjectMapper();
            insurance = oMapper.convertValue(utility.modifyField(updates, insurance), Insurance.class);
            insurance.setModifyDateTime(Calendar.getInstance());
            insuranceRepository.save(insurance);
        }
        return insurance;
    }
}
