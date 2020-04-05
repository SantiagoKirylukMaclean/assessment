package com.technical.assessment.controller;

import com.technical.assessment.model.dto.TokenDTO;
import com.technical.assessment.model.dto.LoginDTO;
import com.technical.assessment.service.AuthServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.version}")
public class AuthController {

    @Autowired
    private AuthServiceInterface authServiceInterface;

    public AuthController(AuthServiceInterface authServiceInterface) {
        this.authServiceInterface = authServiceInterface;
    }

    @PostMapping(value = "/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
        String jwt = authServiceInterface.authUser(loginRequest);

        return ResponseEntity.ok(new TokenDTO(jwt, "Bearer"));
    }

}
