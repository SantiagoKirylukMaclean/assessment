package com.technical.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.Role;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.RoleRepository;
import com.technical.assessment.repository.UserRepository;
import com.technical.assessment.service.UserServiceInterface;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Utility utility;

    public List<User> getUsersByUserName(String username) {
        Optional<User> userHeader = this.getUserByUserName(username);
        List<User> users = new ArrayList<>();
        if (userHeader.isPresent()) {
            users = userRepository.findAll().stream()
                    .filter(u -> u.getInsurance().getId().equals(userHeader.get().getInsurance().getId())).
                            collect(Collectors.toList());
        }
        return users;
    }

    public User getUserByUserNameAndId(String username, String id) {
        Optional<User> userHeader = this.getUserByUserName(username);
        return userRepository.findAll().stream()
                .filter(u -> Objects.equals(u.getInsurance().getId(), userHeader.orElse(new User()).getInsurance().getId()))
                .filter(p -> p.getId().equals(Long.parseLong(id))).findFirst().orElse(new User());
    }

    public User addUser(User user, String username) {
        Optional<User> userHeader = this.getUserByUserName(username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setInsurance(insuranceRepository.findById(userHeader.orElse(new User()).getInsurance().getId()).orElse(new Insurance()));
        user.setActive(1);
        user.setModifyDateTime(Calendar.getInstance());
        List<Role> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(roleRepository.findByRoleName(role.getRoleName()));
        }
        user.setRoles(new HashSet<Role>(roles));
        userRepository.save(user);
        return user;
    }

    public User saveUser(Map<String, Object> updates, String id) {
        User user = userRepository.findById(Long.parseLong(id)).orElse(new User());
        ObjectMapper oMapper = new ObjectMapper();
        user = oMapper.convertValue(utility.modifyField(updates, user), User.class);
        user.setModifyDateTime(Calendar.getInstance());
        userRepository.save(user);
        return user;
    }

    public Optional<User> getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

}
