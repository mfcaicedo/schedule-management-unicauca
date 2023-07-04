package com.pragma.api.controllers;

import com.pragma.api.domain.UserDTO;
import com.pragma.api.security.entities.User;
import com.pragma.api.security.jwt.JwtProvider;
import com.pragma.api.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    /*@GetMapping("/{token}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProvider.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();

        List<String> authorities = claims.get("authorities", List.class);
        UserDTO dto = new UserDTO(username,authorities);
        return new ResponseEntity("Obteniendo usuario", HttpStatus.OK);
    }*/
}
