package com.technical.assessment.controller;

import com.technical.assessment.model.User;
import com.technical.assessment.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0")
public class UserController {

    @Autowired
    UserServiceInterface userServiceInterface;

    @GetMapping("/user")
    public List<User> findAllUser() {

        return userServiceInterface.getAllUser();
    }

}
