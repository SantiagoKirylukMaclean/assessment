package com.technical.assessment.controller;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.security.jwt.JwtProvider;
import com.technical.assessment.service.InsuranceServiceInterface;
import com.technical.assessment.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("${api.version}")
public class InsuranceController {

    @Autowired
    private InsuranceServiceInterface insuranceServiceInterface;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private Utility utility;

    @GetMapping("/insurances")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<Insurance> getAllInsurance(HttpServletRequest headers) {
        return insuranceServiceInterface.getInsuranceByUserName(utility.getUserHeader(headers));
    }

    @PatchMapping(value = "/insurances", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> saveInsurance(@RequestBody Map<String, Object> updates, HttpServletRequest headers) {
        return insuranceServiceInterface.saveInsurance(updates, utility.getUserHeader(headers));
    }

}
