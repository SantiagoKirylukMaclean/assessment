package com.technical.assessment.service;

import com.technical.assessment.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public interface UserServiceInterface {

    List<User> getUsersByUserName(String username);
    Optional<User> getUserByUserName(String username);
    User getUserByUserNameAndId(String username, String id);
    User addUser(User user, String username, Set<String> rolesUpdate);
    User saveUser(Map<String, Object> updates, String id, String username);
    User alterUser(User userUpdate, String username, String userId, Set<String> rolesUpdate);
}
