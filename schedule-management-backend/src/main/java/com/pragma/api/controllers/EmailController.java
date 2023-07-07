package com.pragma.api.controllers;

import com.pragma.api.domain.EmailValuesDTO;
import com.pragma.api.security.entities.User;
import com.pragma.api.security.services.UserService;
import com.pragma.api.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/emailPassword")
@CrossOrigin
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Value("${spring.mail.username}")
    private String mailFrom;


    @GetMapping("/email/send")
    public ResponseEntity<?> sendEmail(){
        emailService.sendEmail();
        return new ResponseEntity("Correo Enviado con exito", HttpStatus.OK);
    }

    @PostMapping("/email/sendHtml")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto){
        Optional<User> usuarioOptional = userService.getByUsernameOrEmail(dto.getMailTo());
        //comprobamos si no existe el usuario
        if(!usuarioOptional.isPresent())
            return new ResponseEntity("No existe ningun usuario con estas credenciales ", HttpStatus.NOT_FOUND);
        User user = usuarioOptional.get();
        dto.setMailFrom(mailFrom);
        dto.setSubject("Cambio de Contraseña");
        dto.setUsername(user.getUsername());
        //Creamos el token
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setToken(tokenPassword);
        user.setTokenPassword(tokenPassword);
        //userService.save(user);
        emailService.sendEmailTemplate(dto);
        return new ResponseEntity("Te hemos enviado un corrreo con un link para cambiar la contraseña", HttpStatus.OK);
    }

}
