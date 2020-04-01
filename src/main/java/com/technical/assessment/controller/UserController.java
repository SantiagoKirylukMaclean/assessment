package com.technical.assessment.controller;

import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.UserDTO;
import com.technical.assessment.security.jwt.JwtProvider;
import com.technical.assessment.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0/insurance")
public class UserController {

    @Autowired
    UserServiceInterface userServiceInterface;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<List<User>> findAllUser(HttpServletRequest headers) {
        return userServiceInterface.getUsersByUserName(this.getUserFromJwtHEader(headers));
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> addUser(@RequestBody UserDTO user, HttpServletRequest headers) {
        return userServiceInterface.addUser(user, this.getUserFromJwtHEader(headers));
    }

    @RequestMapping(value = "/user2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser2(@RequestBody User user) {

        return userServiceInterface.saveUser(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUSer(@RequestBody Map<String, Object> updates, @PathVariable("id") String id) {
        return userServiceInterface.saveUser(updates, id);
    }

    private String getUserFromJwtHEader(HttpServletRequest headers) {
        String authHeader = headers.getHeader("Authorization");
        return jwtProvider.getUserNameFromJwtToken(authHeader.replace("Bearer ", ""));
    }

}
