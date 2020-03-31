package com.technical.assessment.controller;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.Policie;
import com.technical.assessment.service.PolicieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0")
public class PolicieController {

    @Autowired
    PolicieServiceInterface policieServiceInterface;

    @GetMapping("/policies")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public List<Policie> getAllPolicies(HttpServletRequest headers) {
        //String authHeader = headers.getHeader("Authorization");
        //String user = jwtProvider.getUserNameFromJwtToken(authHeader.replace("Bearer ",""));
        return policieServiceInterface.getAllPolicie();
    }
}
