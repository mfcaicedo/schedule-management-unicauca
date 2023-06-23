package com.pragma.api.security.controllers;


import com.pragma.api.security.dto.JwtDTO;
import com.pragma.api.security.dto.LoginDTO;
import com.pragma.api.security.dto.UserDTO;
import com.pragma.api.security.entities.User;
import com.pragma.api.security.jwt.JwtProvider;
import com.pragma.api.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    final
    AuthenticationManager authenticationManager;

    final
    UserService userService;


    @Autowired
    final
    JwtProvider jwtProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping("/new")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity("Invalidate email or bad arguments"
                    , HttpStatus.BAD_REQUEST);
        }

        if(userService.existsByUsername(user.getUsername())){
            return new ResponseEntity("Username already exists"
                    ,HttpStatus.BAD_REQUEST);
        }

        if(userService.existsByEmail(user.getEmail())){
            return new ResponseEntity("Email already exists"
                    ,HttpStatus.BAD_REQUEST);
        }

        //Save new User
        userService.save(user);

        return new ResponseEntity("User register successfully",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity("Bad arguments"
                    , HttpStatus.BAD_REQUEST);
        }
        System.out.println("que sale " + loginDTO.getUsername() + ' ' + loginDTO.getPassword());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (loginDTO.getUsername(),loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        JwtDTO jwtDTO = new JwtDTO(jwt,userDetails.getUsername(),userDetails.getAuthorities());


        return new ResponseEntity(jwtDTO,HttpStatus.OK);
    }
}
