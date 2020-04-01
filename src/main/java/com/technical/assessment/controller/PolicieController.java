package com.technical.assessment.controller;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.Policie;
import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.LoginDTO;
import com.technical.assessment.model.dto.PolicieDTO;
import com.technical.assessment.security.jwt.JwtProvider;
import com.technical.assessment.service.PolicieServiceInterface;
import com.technical.assessment.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/insurances")
public class PolicieController {

    @Autowired
    PolicieServiceInterface policieServiceInterface;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/policies")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<List<Policie>> getAllPolicies(HttpServletRequest headers) {
        return policieServiceInterface.getPolicieByUsername(this.getUserFromJwtHEader(headers));
    }

    @RequestMapping(value = "/policies", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> addPolicie(@RequestBody PolicieDTO policie, HttpServletRequest headers) {
        return policieServiceInterface.savePolicie(policie,this.getUserFromJwtHEader(headers));
    }

    @RequestMapping(value = "/policies/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> savePolicie(@RequestBody Map<String, Object> updates,
                                         HttpServletRequest headers,
                                         @PathVariable("id") String id) {
        return policieServiceInterface.savePolicie(updates, this.getUserFromJwtHEader(headers), id);
    }

    private String getUserFromJwtHEader(HttpServletRequest headers) {
        String authHeader = headers.getHeader("Authorization");
        return jwtProvider.getUserNameFromJwtToken(authHeader.replace("Bearer ", ""));
    }


}
