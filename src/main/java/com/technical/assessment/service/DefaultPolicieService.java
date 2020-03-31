package com.technical.assessment.service;

import com.technical.assessment.model.Policie;
import com.technical.assessment.repository.PolicieRepository;
import org.omg.CORBA.PolicyOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultPolicieService implements PolicieServiceInterface {

    @Autowired
    PolicieRepository policieRepository;

    public List<Policie> getAllPolicie(){
        return policieRepository.findAll();
    }
}
