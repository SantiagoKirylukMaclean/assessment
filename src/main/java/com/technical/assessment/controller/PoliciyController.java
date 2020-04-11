package com.technical.assessment.controller;

import com.technical.assessment.model.Policy;
import com.technical.assessment.model.dto.PolicyRequestDTO;
import com.technical.assessment.model.dto.PolicyResponseDTO;
import com.technical.assessment.service.PolicyServiceInterface;
import com.technical.assessment.utils.UserRoles;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("${api.version}")
public class PoliciyController {

    @Autowired
    private PolicyServiceInterface policyServiceInterface;

    @Autowired
    private Utility utility;

    @Autowired
    private ModelMapper modelMapper;

    public PoliciyController(PolicyServiceInterface policyServiceInterface, Utility utility, ModelMapper modelMapper) {
        this.policyServiceInterface = policyServiceInterface;
        this.utility = utility;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/insurances/policies")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<List<PolicyResponseDTO>> getAllPolicies(HttpServletRequest headers) {
        List<Policy> policiesResponse = policyServiceInterface.getPoliciesByUsername(utility.getUserHeader(headers));
        List<PolicyResponseDTO> policesResponseDTO = new ArrayList<>();

        if (policiesResponse.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(policesResponseDTO);
        }
        for (Policy policy : policiesResponse) {
            policesResponseDTO.add(modelMapper.map(policy, PolicyResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(policesResponseDTO);
    }

    @GetMapping("/insurances/policies/{id}")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<PolicyResponseDTO> getPolicy(HttpServletRequest headers, @PathVariable("id") String id) {
        Policy policyResponse = policyServiceInterface.getPolicyByUsername(utility.getUserHeader(headers), id);
        if (policyResponse.getId() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(modelMapper.map(policyResponse, PolicyResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(policyResponse, PolicyResponseDTO.class));
    }

    @PostMapping(value = "/insurances/policy", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<PolicyResponseDTO> addPolicy(@Valid @RequestBody PolicyRequestDTO policyRequestDTO,
                                                       HttpServletRequest headers) {
        Policy policyRequest = modelMapper.map(policyRequestDTO, Policy.class);
        Policy policyResponse = policyServiceInterface.savePolicy(policyRequest, utility.getUserHeader(headers));
        if (policyResponse.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(modelMapper.map(policyResponse, PolicyResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(policyResponse, PolicyResponseDTO.class));
    }

    @PatchMapping(value = "/insurances/policy/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_PRODUCT_MANAGER)
    public ResponseEntity<PolicyResponseDTO> savePolicy(@RequestBody Map<String, Object> updates,
                                                        HttpServletRequest headers,
                                                        @PathVariable("id") String id) {
        Policy policyResponse = policyServiceInterface.savePolicy(updates, utility.getUserHeader(headers), id);
        if (policyResponse.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(modelMapper.map(policyResponse, PolicyResponseDTO.class));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(policyResponse, PolicyResponseDTO.class));
    }

    @PutMapping(value = "/insurances/policy/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<PolicyResponseDTO> modifyPolicy(@Valid @RequestBody PolicyRequestDTO policyRequestDTO,
                                                          HttpServletRequest headers,
                                                          @PathVariable("id") String id) {
        Policy policyResponse = policyServiceInterface.savePolicy(modelMapper.map(policyRequestDTO, Policy.class),
                utility.getUserHeader(headers),
                id);
        if (policyResponse.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(modelMapper.map(policyResponse, PolicyResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(policyResponse, PolicyResponseDTO.class));
    }


}
