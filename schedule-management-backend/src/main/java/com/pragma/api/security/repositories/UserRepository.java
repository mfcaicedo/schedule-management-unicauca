package com.pragma.api.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pragma.api.security.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
