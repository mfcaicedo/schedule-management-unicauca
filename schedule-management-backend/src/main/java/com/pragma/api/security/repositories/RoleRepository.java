package com.pragma.api.security.repositories;


import com.pragma.api.security.entities.Role;
import com.pragma.api.security.enums.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByTypeRole(TypeRole roleName);
}
