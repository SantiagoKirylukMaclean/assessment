package com.technical.assessment.service;

import com.technical.assessment.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserServiceInterface {

    List<User> getUsersByUserName(String username);
    Optional<User> getUserByUserName(String username);
    User getUserByUserNameAndId(String username, String id);
    User addUser(User user, String username);
    User saveUser(Map<String, Object> updates, String id);
}
