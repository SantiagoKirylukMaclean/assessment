package com.technical.assessment.controller;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.dto.InsuranceResponseDTO;
import com.technical.assessment.security.jwt.JwtProvider;
import com.technical.assessment.service.InsuranceServiceInterface;
import com.technical.assessment.utils.UserRoles;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private ModelMapper modelMapper;

    public InsuranceController(InsuranceServiceInterface insuranceServiceInterface,
                               JwtProvider jwtProvider, Utility utility,
                               ModelMapper modelMapper) {
        this.insuranceServiceInterface = insuranceServiceInterface;
        this.jwtProvider = jwtProvider;
        this.utility = utility;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/insurances")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<InsuranceResponseDTO> getAllInsurance(HttpServletRequest headers) {
        Insurance insuranceResponse = insuranceServiceInterface.getInsuranceByUserName(utility.getUserHeader(headers));
        if (insuranceResponse.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(modelMapper.map(insuranceResponse, InsuranceResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(insuranceResponse, InsuranceResponseDTO.class));
    }

    @PatchMapping(value = "/insurances", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_SUPERVISOR)
    public ResponseEntity<InsuranceResponseDTO> insuranceResponseDTO(@RequestBody Map<String, Object> updates, HttpServletRequest headers) {
        Insurance insuranceResponse =  insuranceServiceInterface.saveInsurance(updates, utility.getUserHeader(headers));
        if (insuranceResponse.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(modelMapper.map(insuranceResponse, InsuranceResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(insuranceResponse, InsuranceResponseDTO.class));
    }

}