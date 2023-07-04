package com.pragma.api.domain;

import com.pragma.api.security.entities.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private Collection<? extends GrantedAuthority> authorities;
}
