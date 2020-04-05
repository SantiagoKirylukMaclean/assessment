package com.technical.assessment.service;

import com.technical.assessment.model.dto.LoginDTO;
import org.springframework.stereotype.Service;

@Service("AuthServiceInterface")
public interface AuthServiceInterface {

    String authUser(LoginDTO loginRequest);
}
