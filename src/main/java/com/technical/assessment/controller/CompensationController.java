package com.technical.assessment.controller;

import com.technical.assessment.model.dto.CompensationResponseDTO;
import com.technical.assessment.service.CompensationServiceInterface;
import com.technical.assessment.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/insurances/compensations/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<List<CompensationResponseDTO>> getClaimsByUserName(HttpServletRequest headers) {
        return compensationServiceInterface.getCompensationsByUserName(utility.getUserHeader(headers));
    }
}
