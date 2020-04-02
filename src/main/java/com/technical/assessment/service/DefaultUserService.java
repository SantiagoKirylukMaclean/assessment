package com.technical.assessment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.assessment.model.Role;
import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.UserDTO;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.RoleRepository;
import com.technical.assessment.repository.UserRepository;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Utility utility;


    public ResponseEntity<List<User>> getUsersByUserName(String username) {
        try {
            Optional<User> user = this.getUserByUserName(username);
            List<User> users = userRepository.findAll().stream()
                    .filter(u -> u.getInsurance().getId().equals(user.get().getInsurance().getId())).
                            collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    public ResponseEntity<User> getUserByUserName(String username, String id) {
        try {
            Optional<User> userHeader = this.getUserByUserName(username);
            User user = userRepository.findAll().stream()
                    .filter(u -> u.getInsurance().getId().equals(userHeader.get().getInsurance().getId()))
                    .filter(p -> p.getId().equals(Long.parseLong(id))).findFirst().get();

            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<?> addUser(UserDTO userDTO, String username){
        User userHeader = userRepository.findByUsername(username).get();

        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setInsurance(insuranceRepository.findById(userHeader.getInsurance().getId()).get());
        user.setActive(1);
        user.setModifyDateTime(Calendar.getInstance());
        List<Role> roles = new ArrayList<>();
        for (String role : userDTO.getRoles()){
            roles.add(roleRepository.findByRoleName(role));
        }

        user.setRoles(new HashSet<Role>(roles));

        try {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("user saved ok");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> saveUser(Map<String, Object> updates, String id){
        //Utility utility = Utility.getInstance();
        try {
            User user = userRepository.findById(Long.parseLong(id)).get();

            ObjectMapper oMapper = new ObjectMapper();
            user = oMapper.convertValue(utility.modifyField(updates,user), User.class);
            user.setModifyDateTime(Calendar.getInstance());

            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("User saved ok");
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insurance not found - " + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public Optional<User> getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

}
