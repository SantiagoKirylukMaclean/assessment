package com.technical.assessment.controller;

import com.technical.assessment.model.Policy;
import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.PolicyRequestDTO;
import com.technical.assessment.model.dto.PolicyResponseDTO;
import com.technical.assessment.repository.PolicyRepository;
import com.technical.assessment.service.PolicyServiceInterface;
import com.technical.assessment.utils.UserRoles;
import com.technical.assessment.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Type;
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

    @GetMapping(value = "/insurances/policies")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public List<PolicyResponseDTO> getAllPolicies(HttpServletRequest headers) {
        List<Policy> policiesResponse = policyServiceInterface.getPoliciesByUsername(utility.getUserHeader(headers));
        List<PolicyResponseDTO> policesResponseDTO = new ArrayList<>();
        for (Policy policy : policiesResponse) {
            policesResponseDTO.add(modelMapper.map(policy, PolicyResponseDTO.class));
        }
        return policesResponseDTO;
    }

    @GetMapping("/insurances/policies/{id}")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public PolicyResponseDTO getPolicy(HttpServletRequest headers, @PathVariable("id") String id){
        Policy policyResponse = policyServiceInterface.getPolicyByUsername(utility.getUserHeader(headers), id);
        PolicyResponseDTO policyResponseDTO = modelMapper.map(policyResponse,PolicyResponseDTO.class);
        return policyResponseDTO;
    }

    @PostMapping(value = "/insurances/policy", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_USER)
    public PolicyResponseDTO addPolicy(@Valid @RequestBody PolicyRequestDTO policyRequestDTO,
                                       HttpServletRequest headers) {
        Policy policyRequest = modelMapper.map(policyRequestDTO, Policy.class);
        Policy policyResponse = policyServiceInterface.savePolicy(policyRequest, utility.getUserHeader(headers));
        PolicyResponseDTO policyResponseDTO = modelMapper.map(policyResponse,PolicyResponseDTO.class);
        return policyResponseDTO;
    }

    @PatchMapping(value = "/insurances/policy/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_PRODUCT_MANAGER)
    public PolicyResponseDTO savePolicie(@RequestBody Map<String, Object> updates,
                                         HttpServletRequest headers,
                                         @PathVariable("id") String id) {
        Policy policyResponse = policyServiceInterface.savePolicy(updates, utility.getUserHeader(headers), id);
        PolicyResponseDTO policyResponseDTO = modelMapper.map(policyResponse,PolicyResponseDTO.class);
        return policyResponseDTO;
    }

}
