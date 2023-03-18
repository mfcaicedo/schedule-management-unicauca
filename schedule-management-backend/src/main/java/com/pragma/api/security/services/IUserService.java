package com.pragma.api.security.services;


import com.pragma.api.security.dto.UserDTO;
import com.pragma.api.security.entities.User;

import java.util.Optional;

public interface IUserService {
    public Optional<User> getByUsername(String username);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
    public void save(UserDTO userDTO);

}
