package com.example.BloggingApiProject.Service;

import com.example.BloggingApiProject.DTO.UserDTO;
import com.example.BloggingApiProject.Model.User;

public interface UserService {

    User createUser(UserDTO userDTO);

    User searchUser(String username);

}
