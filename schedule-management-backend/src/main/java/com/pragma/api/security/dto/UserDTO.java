package com.pragma.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();
}
