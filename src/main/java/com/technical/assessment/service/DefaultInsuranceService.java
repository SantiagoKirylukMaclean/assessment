package com.technical.assessment.service;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.InsuranceDTO;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DefaultInsuranceService implements InsuranceServiceInterface {


    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    UserServiceInterface userServiceInterface;

    public List<Insurance> getAllInsurance() {
        return insuranceRepository.findAll();
    }

    public Optional<Insurance> getInsuranceByUserName(String username) {
        Optional<User> user = userServiceInterface.getUserByUserName(username);
        return insuranceRepository.findAll().stream()
                .filter(i -> i.getId() == user.get().getInsurance().getId()).findFirst();
    }

    public ResponseEntity<?> saveInsurance(Map<String, Object> updates, String id){
        try {
            Insurance insurance = insuranceRepository.findById(Long.parseLong(id)).get();
            updates.forEach((k, v) -> {
                Field field = ReflectionUtils.findField(Insurance.class, k);
                if (!k.equals("id")){
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, insurance, v);
                }
            });
            insuranceRepository.save(insurance);
            return ResponseEntity.status(HttpStatus.OK).body("Insurance saved ok");
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insurance not found - " + e.getMessage());
        }catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Field not found - " + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




}
