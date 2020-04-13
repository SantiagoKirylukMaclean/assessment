package com.technical.assessment.controller;

import com.technical.assessment.model.Claim;
import com.technical.assessment.model.Negotiation;
import com.technical.assessment.model.Policy;
import com.technical.assessment.model.dto.ClaimReachRequestDTO;
import com.technical.assessment.model.dto.ClaimRequestDTO;
import com.technical.assessment.model.dto.ClaimResponseDTO;
import com.technical.assessment.model.dto.ProposalRequestDTO;
import com.technical.assessment.model.dto.RejectRequestDTO;
import com.technical.assessment.service.ClaimServiceInterface;
import com.technical.assessment.utils.UserRoles;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.version}")
public class ClaimController {

    @Autowired
    private ClaimServiceInterface claimServiceInterface;

    @Autowired
    private Utility utility;

    @Autowired
    private ModelMapper modelMapper;

    public ClaimController(ClaimServiceInterface claimServiceInterface, Utility utility, ModelMapper modelMapper) {
        this.claimServiceInterface = claimServiceInterface;
        this.utility = utility;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/insurances/claims/{id}")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<ClaimResponseDTO> getClaimsById(HttpServletRequest headers, @PathVariable("id") String claimId) {
        Claim claimResponse = claimServiceInterface.getClaimsById(utility.getUserHeader(headers), claimId);
        if (claimResponse.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(claimResponse, ClaimResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(claimResponse, ClaimResponseDTO.class));
    }

    @GetMapping("/insurances/claims/guilty")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<List<ClaimResponseDTO>> getClaimsGultyByUserName(HttpServletRequest headers) {
        List<Claim> claimsResponse = claimServiceInterface.getClaimsGuiltyByUserName(utility.getUserHeader(headers));
        List<ClaimResponseDTO> claimsResponseDTO = new ArrayList<>();
        if (claimsResponse.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(claimsResponseDTO);
        }
        for (Claim claim : claimsResponse) {
            claimsResponseDTO.add(modelMapper.map(claim, ClaimResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(claimsResponseDTO);
    }

    @GetMapping("/insurances/claims/victim")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<List<ClaimResponseDTO>> getClaimsVictimByUserName(HttpServletRequest headers) {
        List<Claim> claimsResponse = claimServiceInterface.getClaimsVictimByUserName(utility.getUserHeader(headers));
        List<ClaimResponseDTO> claimsResponseDTO = new ArrayList<>();
        if (claimsResponse.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(claimsResponseDTO);
        }
        for (Claim claim : claimsResponse) {
            claimsResponseDTO.add(modelMapper.map(claim, ClaimResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(claimsResponseDTO);
    }

    @PostMapping(value = "/insurances/claims", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<ClaimResponseDTO> addClaim(@Valid @RequestBody ClaimRequestDTO claimRequestDTO,
                                                     HttpServletRequest headers) throws Exception {
        Claim claimRequest = modelMapper.map(claimRequestDTO, Claim.class);

        Policy policyVictim = new Policy();
        policyVictim.setId(claimRequestDTO.getPolicyVictim());
        claimRequest.setPolicyVictim(policyVictim);

        Policy policyGuilty = new Policy();
        policyGuilty.setId(claimRequestDTO.getPolicyGuilty());
        claimRequest.setPolicyGuilty(policyGuilty);

        Claim claimResponse = claimServiceInterface.addClaim(claimRequest, utility.getUserHeader(headers));
        if (claimResponse.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(claimResponse, ClaimResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(claimResponse, ClaimResponseDTO.class));
    }

    @PostMapping(value = "/insurances/claims/reject/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<ClaimResponseDTO> rejectClaim(@Valid @RequestBody RejectRequestDTO rejectRequestDTO,
                                                        HttpServletRequest headers,
                                                        @PathVariable("id") String id) {
        Claim claimResponse = claimServiceInterface.rejectClaim(id, modelMapper.map(rejectRequestDTO, Negotiation.class),
                utility.getUserHeader(headers));
        if (claimResponse.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(claimResponse, ClaimResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(claimResponse, ClaimResponseDTO.class));
    }

    @PostMapping(value = "/insurances/claims/proposal/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_USER)
    public ResponseEntity<ClaimResponseDTO> proposalClaim(@Valid @RequestBody ProposalRequestDTO proposalRequestDTO, HttpServletRequest headers,
                                          @PathVariable("id") String id) {
        Claim claimResponse = claimServiceInterface.proposalClaim(id, modelMapper.map(proposalRequestDTO, Negotiation.class),
                utility.getUserHeader(headers));
        if (claimResponse.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(claimResponse, ClaimResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(claimResponse, ClaimResponseDTO.class));
    }

    @PostMapping(value = "/insurances/claims/reach/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_PRODUCT_MANAGER)
    public ResponseEntity<ClaimResponseDTO> reachClaim(@Valid @RequestBody ClaimReachRequestDTO claimReachRequestDTO,
                                                       HttpServletRequest headers,
                                                       @PathVariable("id") String id) {
        Claim claimResponse = claimServiceInterface.reachClaim(id, modelMapper.map(claimReachRequestDTO, Negotiation.class),
                utility.getUserHeader(headers));
        if (claimResponse.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(claimResponse, ClaimResponseDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(claimResponse, ClaimResponseDTO.class));
    }

}
