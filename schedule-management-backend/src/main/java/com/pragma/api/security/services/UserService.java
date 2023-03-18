package com.pragma.api.security.services;


import com.pragma.api.security.dto.UserDTO;
import com.pragma.api.security.entities.Role;
import com.pragma.api.security.entities.User;
import com.pragma.api.security.enums.TypeRole;
import com.pragma.api.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IRoleService roleService;


    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getLastName(),
                userDTO.getUsername(), userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()));


        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByTypeRole(TypeRole.ROLE_USER).get());

        if(userDTO.getRoles().contains("admin")){
            roles.add(roleService.getByTypeRole(TypeRole.ROLE_ADMIN).get());
        }
        user.setRoles(roles);
        userRepository.save(user);
    }
}
