package com.technical.assessment.controller;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.security.jwt.JwtProvider;
import com.technical.assessment.service.InsuranceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0")
public class InsuranceController {

    @Autowired
    InsuranceServiceInterface insuranceServiceInterface;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/insurances")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public Optional<Insurance> getAllInsurance(HttpServletRequest headers) {
        String authHeader = headers.getHeader("Authorization");
        String user = jwtProvider.getUserNameFromJwtToken(authHeader.replace("Bearer ",""));
        return insuranceServiceInterface.getInsuranceByUserName(user);
    }

    @RequestMapping(value = "/insurances/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveInsurance(@RequestBody Map<String, Object> updates, @PathVariable("id") String id) {
        return insuranceServiceInterface.saveInsurance(updates, id);
    }




}
