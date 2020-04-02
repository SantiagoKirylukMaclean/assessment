package com.technical.assessment.controller;

import com.technical.assessment.model.User;
import com.technical.assessment.model.dto.UserDTO;
import com.technical.assessment.security.jwt.JwtProvider;
import com.technical.assessment.service.UserServiceInterface;
import com.technical.assessment.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.version}")
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private Utility utility;

    @GetMapping("/insurances/users")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<List<User>> findAllUser(HttpServletRequest headers) {
        return userServiceInterface.getUsersByUserName(utility.getUserHeader(headers));
    }

    @GetMapping("/insurances/user/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<User> getUser(HttpServletRequest headers,
                                       @PathVariable("id") String id) throws Exception {
        return userServiceInterface.getUserByUserName(utility.getUserHeader(headers), id);
    }

    @PostMapping(value = "/insurances/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_SUP')")
    public ResponseEntity<?> addUser(@RequestBody UserDTO user, HttpServletRequest headers) {
        return userServiceInterface.addUser(user, utility.getUserHeader(headers));
    }

    @PatchMapping(value = "/insurances/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody Map<String, Object> updates, @PathVariable("id") String id) {
        return userServiceInterface.saveUser(updates, id);
    }

}
