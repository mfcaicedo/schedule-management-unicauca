package com.pragma.api.controllers;

import com.pragma.api.domain.UserDTO;
import com.pragma.api.security.entities.User;
import com.pragma.api.security.jwt.JwtProvider;
import com.pragma.api.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/usercontroller")
@CrossOrigin(origins = "http://localhost:4200/#")
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username){
        User user = userService.getByUsername(username).orElse(null);
        if(user==null)
            return new ResponseEntity("No existe ningun usuario con ese username ", HttpStatus.NOT_FOUND);

        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getTypeRole().name()))
                .collect(Collectors.toList());

        dto.setAuthorities(authorities);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
