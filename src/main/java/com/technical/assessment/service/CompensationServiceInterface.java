package com.technical.assessment.service;

import com.technical.assessment.model.dto.CompensationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompensationServiceInterface {
    ResponseEntity<List<CompensationResponseDTO>> getCompensationsByUserName(String headerUsername);
}
