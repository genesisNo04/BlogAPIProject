package com.example.BloggingApiProject.Common;

import com.example.BloggingApiProject.DTO.RoleDTO;
import com.example.BloggingApiProject.Service.RoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
        if (roleService.searchRole("ROLE_USER") == null) {
            roleService.createRole(new RoleDTO("ROLE_USER"));
        }

        if (roleService.searchRole("ROLE_ADMIN") == null) {
            roleService.createRole(new RoleDTO("ROLE_ADMIN"));
        }
    }
}
