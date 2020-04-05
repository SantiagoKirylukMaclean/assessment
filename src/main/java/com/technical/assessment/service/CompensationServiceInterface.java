package com.technical.assessment.service;

import com.technical.assessment.model.Compensation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompensationServiceInterface {
    List<Compensation> getCompensationsByUserName(String headerUsername);
}
