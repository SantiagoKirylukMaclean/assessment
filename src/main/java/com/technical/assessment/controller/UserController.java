package com.technical.assessment.controller;

import com.technical.assessment.model.Policy;
import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.PolicyResponseDTO;
import com.technical.assessment.model.dto.UserRequestDTO;
import com.technical.assessment.model.dto.UserResponseDTO;
import com.technical.assessment.service.UserServiceInterface;
import com.technical.assessment.utils.UserRoles;
import com.technical.assessment.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.version}")
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private Utility utility;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/insurances/users")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public List<UserResponseDTO> findAllUser(HttpServletRequest headers) {
        List<User> usersResponse = userServiceInterface.getUsersByUserName(utility.getUserHeader(headers));
        List<UserResponseDTO> usersResponseDTO = new ArrayList<>();
        for (User user : usersResponse) {
            usersResponseDTO.add(modelMapper.map(user, UserResponseDTO.class));
        }
        return usersResponseDTO;
    }

    @GetMapping("/insurances/user/{id}")
    @PreAuthorize(UserRoles.LOGGED_USER)
    public UserResponseDTO getUser(HttpServletRequest headers,
                                       @PathVariable("id") String id) {
        User userResponse = userServiceInterface.getUserByUserNameAndId(utility.getUserHeader(headers), id);
        return modelMapper.map(userResponse, UserResponseDTO.class);
    }

    @PostMapping(value = "/insurances/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_PRODUCT_MANAGER)
    public UserResponseDTO addUser(@RequestBody UserRequestDTO userRequestDTO, HttpServletRequest headers) {
        User userResponse = userServiceInterface.addUser(modelMapper.map(userRequestDTO, User.class), utility.getUserHeader(headers));
        return modelMapper.map(userResponse, UserResponseDTO.class);
    }

    @PatchMapping(value = "/insurances/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(UserRoles.LOGGED_PRODUCT_MANAGER)
    public UserResponseDTO saveUser(@RequestBody Map<String, Object> updates, @PathVariable("id") String id) {
        User userResponse = userServiceInterface.saveUser(updates, id);
        return modelMapper.map(userResponse, UserResponseDTO.class);
    }

}
