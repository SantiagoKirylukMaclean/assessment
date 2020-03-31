package com.technical.assessment.service;

import com.technical.assessment.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserServiceInterface {

    List<User> getAllUser();
    Optional<User> getUserByUserName(String username);
}
