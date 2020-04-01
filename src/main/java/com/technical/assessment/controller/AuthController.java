package com.technical.assessment.controller;


import com.technical.assessment.model.dto.TokenDTO;
import com.technical.assessment.model.dto.LoginDTO;
import com.technical.assessment.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1.0/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

        String jwt = authService.authUser(loginRequest);
        return ResponseEntity.ok(new TokenDTO(jwt, "Bearer"));

    }


}