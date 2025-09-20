package com.example.BloggingApiProject.Service.Impl;

import com.example.BloggingApiProject.DTO.UserDTO;
import com.example.BloggingApiProject.Exception.AlreadyExistException;
import com.example.BloggingApiProject.Exception.ResourceNotFoundException;
import com.example.BloggingApiProject.Model.Role;
import com.example.BloggingApiProject.Model.User;
import com.example.BloggingApiProject.Repository.RoleRepository;
import com.example.BloggingApiProject.Repository.UserRepository;
import com.example.BloggingApiProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new AlreadyExistException("User already exist with username: " + userDTO.getUsername());
        }

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        List<Role> roles = userDTO.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new ResourceNotFoundException("No role with name: " + roleName)))
                .collect(Collectors.toList());

        User user = new User(userDTO.getUsername(), encodedPassword, roles, LocalDateTime.now(), LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public User searchUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }
}
