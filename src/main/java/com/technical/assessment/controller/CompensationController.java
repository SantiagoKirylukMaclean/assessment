package com.technical.assessment.controller;

import com.technical.assessment.model.Compensation;
import com.technical.assessment.model.dto.CompensationResponseDTO;
import com.technical.assessment.service.CompensationServiceInterface;
import com.technical.assessment.utils.UserRoles;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.version}")
public class CompensationController {

    @Autowired
    private CompensationServiceInterface compensationServiceInterface;

    @Autowired
    private Utility utility;

    @Autowired
    private ModelMapper modelMapper;

    public CompensationController(CompensationServiceInterface compensationServiceInterface,
                                  Utility utility, ModelMapper modelMapper) {
        this.compensationServiceInterface = compensationServiceInterface;
        this.utility = utility;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/insurances/compensations/")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<List<CompensationResponseDTO>> getClaimsByUserName(HttpServletRequest headers) {
        List<Compensation> compensationsResponse =  compensationServiceInterface
                .getCompensationsByUserName(utility.getUserHeader(headers));
        List<CompensationResponseDTO> compensationsResponseDTO = new ArrayList<>();
        if (compensationsResponse.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(compensationsResponseDTO);
        }
        for (Compensation compensation : compensationsResponse){
            compensationsResponseDTO.add(modelMapper.map(compensation, CompensationResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(compensationsResponseDTO);
    }
}
