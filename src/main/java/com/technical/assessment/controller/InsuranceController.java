package com.technical.assessment.controller;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InsuranceController {

    //TODO:BORRAR
    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/insurance")
    public List<Insurance> findAllInsurance() {

        return insuranceRepository.findAll();
    }

    @GetMapping("/user")
    public List<User> findAllUser() {

        return userRepository.findAll();
    }
}
