package com.technical.assessment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.Policie;
import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.PolicieDTO;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.PolicieRepository;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultPolicieService implements PolicieServiceInterface {

    @Autowired
    PolicieRepository policieRepository;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    UserServiceInterface userServiceInterface;

    @Autowired
    private Utility utility;


    @Autowired
    ModelMapper modelMapper;

    public List<Policie> getAllPolicie(){
        return policieRepository.findAll();
    }

    public ResponseEntity<List<Policie>> getPolicieByUsername(String username) {

        try {
            Optional<User> user = userServiceInterface.getUserByUserName(username);
            List<Policie> policie = policieRepository.findAll().stream()
                    .filter(p -> p.getInsurance().getId().equals(user.get().getInsurance().getId())).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(policie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    public ResponseEntity<?> savePolicie(PolicieDTO policieDTO, String username){

        Policie policie = modelMapper.map(policieDTO,Policie.class);
        Optional<User> user = userServiceInterface.getUserByUserName(username);
        policie.setInsurance(insuranceRepository.findById(user.get().getInsurance().getId()).get());
        try {
            policieRepository.save(policie);
            return ResponseEntity.status(HttpStatus.OK).body("policie saved ok");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> savePolicie(Map<String, Object> updates, String username, String policieId) {
        Optional<User> user = userServiceInterface.getUserByUserName(username);
        //Utility utility = Utility.getInstance();
        try {
            Policie policie = policieRepository.findById(Long.parseLong(policieId)).get();

            ObjectMapper oMapper = new ObjectMapper();
            policie = oMapper.convertValue(utility.modifyField(updates, policie), Policie.class);
            policie.setModifyDateTime(Calendar.getInstance());
            policieRepository.save(policie);

            return ResponseEntity.status(HttpStatus.CREATED).body("Policie has updated");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insurance do not exist - " + e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }


}
