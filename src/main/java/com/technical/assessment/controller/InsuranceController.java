package com.technical.assessment.controller;

import com.technical.assessment.model.Claim;
import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.Negotiation;
import com.technical.assessment.model.dto.ClaimResponseDTO;
import com.technical.assessment.model.dto.InsuranceRequestDTO;
import com.technical.assessment.model.dto.InsuranceResponseDTO;
import com.technical.assessment.security.jwt.JwtProvider;
import com.technical.assessment.service.InsuranceServiceInterface;
import com.technical.assessment.utils.UserRoles;
import com.technical.assessment.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public ResponseEntity<InsuranceResponseDTO> obtainAllInsurances(HttpServletRequest headers) {
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
    public ResponseEntity<InsuranceResponseDTO> modifyInsurance(@RequestBody Map<String, Object> updates, HttpServletRequest headers) {
        Insurance insuranceResponse =  insuranceServiceInterface.updateFieldsInsurance(updates, utility.getUserHeader(headers));
        if (insuranceResponse.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(modelMapper.map(insuranceResponse, InsuranceResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(insuranceResponse, InsuranceResponseDTO.class));
    }

    @PutMapping(value = "/insurances", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_SUPERVISOR)
    public ResponseEntity<Insurance> modifyInsurance(@Valid @RequestBody InsuranceRequestDTO insuranceRequestDTO,
                                                     HttpServletRequest headers) {
        Insurance insuranceResponse = insuranceServiceInterface.updateInsurance(modelMapper.map(insuranceRequestDTO, Insurance.class),
                utility.getUserHeader(headers));
        if (insuranceResponse.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(insuranceResponse, Insurance.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(insuranceResponse, Insurance.class));
    }

}