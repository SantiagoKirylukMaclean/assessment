package com.technical.assessment.service;

import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserServiceInterface {

    ResponseEntity<List<User>> getUsersByUserName(String username);
    Optional<User> getUserByUserName(String username);
    public ResponseEntity<?> addUser(UserDTO userDTO, String username);
    ResponseEntity<?> saveUser(Map<String, Object> updates, String id);
    ResponseEntity<?> saveUser(User user);
}
