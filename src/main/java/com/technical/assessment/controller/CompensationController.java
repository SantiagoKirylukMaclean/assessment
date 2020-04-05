package com.technical.assessment.controller;

import com.technical.assessment.model.Compensation;
import com.technical.assessment.model.dto.CompensationResponseDTO;
import com.technical.assessment.service.CompensationServiceInterface;
import com.technical.assessment.utils.UserRoles;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("${api.version}")
public class CompensationController {

    @Autowired
    CompensationServiceInterface compensationServiceInterface;

    @Autowired
    Utility utility;

    @Autowired
    private ModelMapper modelMapper;

    public CompensationController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("/insurances/compensations/")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public List<CompensationResponseDTO> getClaimsByUserName(HttpServletRequest headers) {
        List<Compensation> compensationResponse =  compensationServiceInterface
                .getCompensationsByUserName(utility.getUserHeader(headers));
        List<CompensationResponseDTO> compensationsResponseDTO = null;
        for (Compensation compensation : compensationResponse){
            compensationsResponseDTO.add(modelMapper.map(compensation, CompensationResponseDTO.class));
        }
        return compensationsResponseDTO;
    }
}
