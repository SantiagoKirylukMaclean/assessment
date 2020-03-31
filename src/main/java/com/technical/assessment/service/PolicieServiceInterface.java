package com.technical.assessment.service;

import com.technical.assessment.model.Policie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PolicieServiceInterface {

    List<Policie> getAllPolicie();
}
