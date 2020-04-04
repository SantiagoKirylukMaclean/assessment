package com.technical.assessment.controller;

import com.technical.assessment.model.Claim;
import com.technical.assessment.model.dto.ClaimRequestDTO;
import com.technical.assessment.model.dto.OfferRequestDTO;
import com.technical.assessment.model.dto.RejectRequestDTO;
import com.technical.assessment.service.ClaimServiceInterface;
import com.technical.assessment.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("${api.version}")
public class ClaimController {

    @Autowired
    private ClaimServiceInterface claimServiceInterface;

    @Autowired
    private Utility utility;

    @GetMapping("/insurances/claims")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<List<Claim>> getClaimsByUserName(HttpServletRequest headers) {
        return claimServiceInterface.getClaimsByUserName(utility.getUserHeader(headers));
    }

    @GetMapping("/insurances/claims/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<Claim> getClaimsById(HttpServletRequest headers, @PathVariable("id") String claimId) {
        return claimServiceInterface.getClaimsById(utility.getUserHeader(headers), claimId);
    }

    @GetMapping("/insurances/claims/guilty")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<List<Claim>> getClaimsGultyByUserName(HttpServletRequest headers) {
        return claimServiceInterface.getClaimsGultyByUserName(utility.getUserHeader(headers));
    }

    @GetMapping("/insurances/claims/victim")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<List<Claim>> getClaimsVictimByUserName(HttpServletRequest headers) {
        return claimServiceInterface.getClaimsVictimByUserName(utility.getUserHeader(headers));
    }

    @PostMapping(value = "/insurances/claims", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> addClaim(@RequestBody ClaimRequestDTO claim, HttpServletRequest headers) {
        return claimServiceInterface.addClaim(claim, utility.getUserHeader(headers));
    }

    @PostMapping(value = "/insurances/claims/reject/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> rejectClaim(@Valid @RequestBody  RejectRequestDTO rejectRequestDTO, HttpServletRequest headers,
                                         @PathVariable("id") String id) {
        return claimServiceInterface.rejectClaim(id, rejectRequestDTO, utility.getUserHeader(headers));
    }

    @PostMapping(value = "/insurances/claims/proposal/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> proposalClaim(@Valid @RequestBody OfferRequestDTO offerRequestDTO, HttpServletRequest headers,
                                         @PathVariable("id") String id) {
        return claimServiceInterface.proposalClaim(id, offerRequestDTO, utility.getUserHeader(headers));
    }

    @PostMapping(value = "/insurances/claims/reach/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> reachClaim(@Valid @RequestBody RejectRequestDTO rejectRequestDTO, HttpServletRequest headers,
                                         @PathVariable("id") String id) {
        return claimServiceInterface.reachClaim(id, rejectRequestDTO, utility.getUserHeader(headers));
    }

    /*
    @GetMapping("/insurances/user/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<User> getUser(HttpServletRequest headers,
                                       @PathVariable("id") String id) throws Exception {
        return userServiceInterface.getUserByUserName(utility.getUserHeader(headers), id);
    }

    @PostMapping(value = "/insurances/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> addUser(@RequestBody UserDTO user, HttpServletRequest headers) {
        return userServiceInterface.addUser(user, utility.getUserHeader(headers));
    }

    @PatchMapping(value = "/insurances/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody Map<String, Object> updates, @PathVariable("id") String id) {
        return userServiceInterface.saveUser(updates, id);
    }
    */
}
