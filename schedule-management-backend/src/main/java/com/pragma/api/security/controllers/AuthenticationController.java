package com.pragma.api.security.controllers;


import com.pragma.api.security.dto.JwtDTO;
import com.pragma.api.security.dto.LoginDTO;
import com.pragma.api.security.dto.UserDTO;
import com.pragma.api.security.jwt.JwtProvider;
import com.pragma.api.security.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    IUserService userService;


    final
    JwtProvider jwtProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, IUserService userService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping("/new")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity("Invalidate email or bad arguments"
                    , HttpStatus.BAD_REQUEST);
        }

        if(userService.existsByUsername(userDTO.getUsername())){
            return new ResponseEntity("Username already exists"
                    ,HttpStatus.BAD_REQUEST);
        }

        if(userService.existsByEmail(userDTO.getEmail())){
            return new ResponseEntity("Email already exists"
                    ,HttpStatus.BAD_REQUEST);
        }

        //Save new User
        userService.save(userDTO);

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
