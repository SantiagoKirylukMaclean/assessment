package com.technical.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.Role;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.RoleRepository;
import com.technical.assessment.repository.UserRepository;
import com.technical.assessment.service.UserServiceInterface;
import com.technical.assessment.utils.TextMessages;
import com.technical.assessment.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultUserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Utility utility;

    @Autowired
    private ObjectMapper objectMapper;

    public DefaultUserService(UserRepository userRepository, ModelMapper modelMapper,
                              InsuranceRepository insuranceRepository, RoleRepository roleRepository,
                              PasswordEncoder passwordEncoder, Utility utility, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.insuranceRepository = insuranceRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.utility = utility;
        this.objectMapper = objectMapper;
    }

    public List<User> getUsersByUserName(String username) {
        Optional<User> userHeader = this.getUserByUserName(username);
        return userRepository.findAll().stream()
                .filter(u -> u.getInsurance().getId().equals(userHeader.orElse(new User()).getInsurance().getId())).
                        collect(Collectors.toList());
    }

    public User getUserByUserNameAndId(String username, String id) {
        Optional<User> userHeader = this.getUserByUserName(username);
        return userRepository.findAll().stream()
                .filter(u -> Objects.equals(u.getInsurance().getId(), userHeader.orElse(new User()).getInsurance().getId()))
                .filter(p -> p.getId().equals(Long.parseLong(id))).findFirst().orElse(new User());
    }

    public User addUser(User user, String username) {
        Optional<User> userHeader = this.getUserByUserName(username);
        Optional<User> newUser = this.getUserByUserName(user.getUsername());
        if (user.getUsername().equals(newUser.orElse(new User()).getUsername())) {
            log.debug(TextMessages.USER_EXIST);
            return new User();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setInsurance(insuranceRepository.findById(userHeader.orElse(new User()).getInsurance().getId()).orElse(new Insurance()));
        user.setActive(1);
        user.setModifyDateTime(Calendar.getInstance());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByRoleName("ROLE_USER"));
        user.setRoles(new HashSet<Role>(roles));
        userRepository.save(user);
        return user;
    }

    public User alterUser(User userUpdate, String username, String userId, Set<String> rolesUpdate) {

        if (userUpdate.getUsername()
                .equals(this.getUserByUserName(userUpdate.getUsername()).orElse(new User()).getUsername())) {
            log.debug(TextMessages.USER_EXIST);
            return new User();
        }
        userUpdate.setId(Long.parseLong(userId));
        userUpdate.setInsurance(insuranceRepository.findById(this.getUserByUserName(username).orElse(new User()).getInsurance().getId()).orElse(new Insurance()));
        userUpdate.setActive(1);
        userUpdate.setModifyDateTime(Calendar.getInstance());
        List<Role> roles = new ArrayList<>();
        for(String role : rolesUpdate){
            roles.add(roleRepository.findByRoleName(role));
        }
        userUpdate.setRoles(new HashSet<Role>(roles));
        return userRepository.save(userUpdate);
    }

    public User saveUser(Map<String, Object> updates, String id, String username) {
        Optional<User> userHeader = this.getUserByUserName(username);
        User user = userRepository.findById(Long.parseLong(id)).orElse(new User());
        if (user.getId() == null){
            log.debug(TextMessages.OBJECT_NOT_EXIST);
            return new User();
        }
        if (!userHeader.orElse(new User()).getInsurance().getId().equals(user.getInsurance().getId())) {
            log.debug(TextMessages.LOGGED_USER_NOT_INSURANCE);
            return new User();
        }
        user = objectMapper.convertValue(utility.modifyField(updates, user), User.class);
        user.setModifyDateTime(Calendar.getInstance());
        userRepository.save(user);
        return user;
    }

    public Optional<User> getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

}
