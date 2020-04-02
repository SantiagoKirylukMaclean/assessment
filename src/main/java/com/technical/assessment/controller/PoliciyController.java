package com.technical.assessment.controller;

import com.technical.assessment.model.Policy;
import com.technical.assessment.model.dto.PolicyDTO;
import com.technical.assessment.service.PolicyServiceInterface;
import com.technical.assessment.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("${api.version}")
public class PoliciyController {

    @Autowired
    private PolicyServiceInterface policyServiceInterface;

    @Autowired
    private Utility utility;

    @GetMapping("/insurances/policies")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<List<Policy>> getAllPolicies(HttpServletRequest headers) {
        return policyServiceInterface.getPolicyByUsername(utility.getUserHeader(headers));
    }

    @GetMapping("/insurances/policies/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<Policy> getPolicy(HttpServletRequest headers,
                                                      @PathVariable("id") String id) throws Exception {
        return policyServiceInterface.getPolicyByUsername(utility.getUserHeader(headers), id);
    }

    @PostMapping(value = "/insurances/policy", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> addPolicy(@Valid @RequestBody PolicyDTO policie,
                                        HttpServletRequest headers) {
        return policyServiceInterface.savePolicy(policie, utility.getUserHeader(headers));
    }

    @PatchMapping(value = "/insurances/policy/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> savePolicie(@RequestBody Map<String, Object> updates,
                                         HttpServletRequest headers,
                                         @PathVariable("id") String id) {
        return policyServiceInterface.savePolicy(updates, utility.getUserHeader(headers), id);
    }

}
