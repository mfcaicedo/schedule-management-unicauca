package com.pragma.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class LoginDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
