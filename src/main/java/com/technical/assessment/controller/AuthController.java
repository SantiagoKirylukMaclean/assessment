package com.technical.assessment.controller;

import com.technical.assessment.model.dto.TokenDTO;
import com.technical.assessment.model.dto.LoginDTO;
import com.technical.assessment.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.version}")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
        String jwt = authService.authUser(loginRequest);
        return ResponseEntity.ok(new TokenDTO(jwt, "Bearer"));
    }

}
