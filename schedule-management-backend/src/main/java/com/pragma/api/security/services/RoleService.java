package com.pragma.api.security.services;

import com.pragma.api.security.entities.Role;
import com.pragma.api.security.enums.TypeRole;
import com.pragma.api.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> getByTypeRole(TypeRole roleName) {
        return roleRepository.findByTypeRole(roleName);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
