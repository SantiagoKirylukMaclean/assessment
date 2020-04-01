package com.technical.assessment.service;

import com.technical.assessment.model.Policie;
import com.technical.assessment.model.dto.PolicieDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PolicieServiceInterface {

    List<Policie> getAllPolicie();
    ResponseEntity<List<Policie>> getPolicieByUsername(String username);
    public ResponseEntity<?> savePolicie(PolicieDTO policieDTO, String username);
    public ResponseEntity<?> savePolicie(Map<String, Object> updates, String username, String policieId);
}
