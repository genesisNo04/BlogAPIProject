package com.example.BloggingApiProject.Controller;

import com.example.BloggingApiProject.DTO.UserDTO;
import com.example.BloggingApiProject.Model.User;
import com.example.BloggingApiProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User saveUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }
}
