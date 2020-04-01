package com.technical.assessment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefaultInsuranceService implements InsuranceServiceInterface {


    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    UserServiceInterface userServiceInterface;

    @Autowired
    private Utility utility;

    public List<Insurance> getAllInsurance() {
        return insuranceRepository.findAll();
    }

    public ResponseEntity<Insurance> getInsuranceByUserName(String username) {

        try {
            Optional<User> user = userServiceInterface.getUserByUserName(username);
            Insurance insurance = insuranceRepository.findAll().stream()
                    .filter(i -> i.getId() == user.get().getInsurance().getId()).findFirst().get();

            return ResponseEntity.status(HttpStatus.OK).body(insurance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    public ResponseEntity<?> saveInsurance(Map<String, Object> updates, String username) {
        Optional<User> user = userServiceInterface.getUserByUserName(username);
        //Utility utility = Utility.getInstance();
        try {
            Insurance insurance = insuranceRepository.findById(Long.parseLong(user.get().getInsurance().getId().toString())).get();

            ObjectMapper oMapper = new ObjectMapper();
            insurance = oMapper.convertValue(utility.modifyField(updates, insurance), Insurance.class);
            insurance.setModifyDateTime(Calendar.getInstance());
            insuranceRepository.save(insurance);

            return ResponseEntity.status(HttpStatus.CREATED).body("Insurace has updated");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insurance do not exist - " + e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }


}
