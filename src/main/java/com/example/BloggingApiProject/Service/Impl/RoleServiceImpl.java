package com.example.BloggingApiProject.Service.Impl;

import com.example.BloggingApiProject.DTO.RoleDTO;
import com.example.BloggingApiProject.Exception.AlreadyExistException;
import com.example.BloggingApiProject.Exception.ResourceNotFoundException;
import com.example.BloggingApiProject.Model.Role;
import com.example.BloggingApiProject.Repository.RoleRepository;
import com.example.BloggingApiProject.Service.RoleService;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(RoleDTO roleDTO) {
        if (roleRepository.findByName(roleDTO.getName()).isPresent()) {
            throw new AlreadyExistException("Role already exist with name: " + roleDTO.getName());
        }

        Role role = new Role();
        role.setName(roleDTO.getName());

        return roleRepository.save(role);
    }

    @Override
    public Role searchRole(String name) {
        return roleRepository.findByName(name).orElse(null);
    }
}
