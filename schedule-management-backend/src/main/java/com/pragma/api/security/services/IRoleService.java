package com.pragma.api.security.services;

import com.pragma.api.security.entities.Role;
import com.pragma.api.security.enums.TypeRole;

import java.util.Optional;


public interface IRoleService {
    public Optional<Role> getByTypeRole(TypeRole roleName);
    public void save(Role role);
}
