package com.example.BloggingApiProject.Service;

import com.example.BloggingApiProject.DTO.RoleDTO;
import com.example.BloggingApiProject.Model.Role;

public interface RoleService {

    Role createRole(RoleDTO roleDTO);

    Role searchRole(String name);
}
