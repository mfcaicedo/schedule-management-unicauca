package com.pragma.api.configuration;

import com.pragma.api.security.entities.Role;
import com.pragma.api.security.entities.User;
import com.pragma.api.security.enums.TypeRole;
import com.pragma.api.security.repositories.RoleRepository;
import com.pragma.api.security.repositories.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class InitialDataConfig {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public InitialDataConfig(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    //El usuario tiene los dos roles
    /*@PostConstruct
    public void addInitialData() {
        List<Role> roles = this.roleRepository.findAll();
        Set<Role> roleSet = new HashSet<>(roles);
        User userOne = User.builder()
                .email("mfcaicedo@unicauca.edu.co")
                .name("Milthon Ferney")
                .lastName("Caicedo Jurador")
                .username("mfcaicedo")
                .password(encoder.encode("mfcaicedo"))
                .roles(roleSet)
                .build();
        User userTwo = User.builder()
                .email("admin@unicauca.edu.co")
                .name("Admin")
                .lastName("Admin")
                .username("admin")
                .password(encoder.encode("admin"))
                .roles(roleSet)
                .build();
        List<User> initialUserList = Arrays.asList(userOne, userTwo);
        userRepository.saveAll(initialUserList);

    }*/
    //Un solo rol por usuario
    @PostConstruct
    public void addInitialData() {
        List<Role> roles = this.roleRepository.findAll();

        Role scheduleManagerRole = null;
        Role academicManagerRole = null;

        for (Role role : roles) {
            if (role.getTypeRole() == TypeRole.ROLE_SCHEDULE_MANAGER) {
                scheduleManagerRole = role;
            } else if (role.getTypeRole() == TypeRole.ROLE_ACADEMIC_MANAGER) {
                academicManagerRole = role;
            }
        }

        User userOne = User.builder()
                .email("mfcaicedo@unicauca.edu.co")
                .name("Milthon Ferney")
                .lastName("Caicedo Jurado")
                .username("mfcaicedo")
                .password(encoder.encode("mfcaicedo"))
                .roles(Collections.singleton(scheduleManagerRole))
                .build();
        User userTwo = User.builder()
                .email("admin@unicauca.edu.co")
                .name("Admin")
                .lastName("Admin")
                .username("admin")
                .password(encoder.encode("admin"))
                .roles(Collections.singleton(academicManagerRole))
                .build();
        
        List<User> initialUserList = Arrays.asList(userOne, userTwo);
        userRepository.saveAll(initialUserList);
    }
}
