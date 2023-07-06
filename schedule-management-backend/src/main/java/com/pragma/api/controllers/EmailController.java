package com.pragma.api.controllers;

import com.pragma.api.domain.ChangePasswordDTO;
import com.pragma.api.domain.EmailValuesDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.security.entities.User;
import com.pragma.api.security.services.UserService;
import com.pragma.api.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    private static final String subject = "Cambio de Contraseña";

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/email/send")
    public ResponseEntity<?> sendEmail(){
        emailService.sendEmail();
        return new ResponseEntity("Correo Enviado con exito", HttpStatus.OK);
    }

    @PostMapping("/email/sendHtml")
    public Response<String> sendEmailTemplate(@RequestBody EmailValuesDTO dto){
        Response response = new Response();

        Optional<User> usuarioOptional = userService.getByUsernameOrEmail(dto.getMailTo());
        //comprobamos si no existe el usuario
        if(!usuarioOptional.isPresent()) {
            response.setUserMessage("No existe ningun usuario con estas credenciales ");
            response.setStatus(404);
            return response;
            /*return new ResponseEntity("No existe ningun usuario con estas credenciales ", HttpStatus.NOT_FOUND);*/
        }
        User user = usuarioOptional.get();
        dto.setMailFrom(mailFrom);
        dto.setMailTo(user.getEmail());
        dto.setSubject(subject);
        dto.setUsername(user.getUsername());
        //Creamos el token
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setToken(tokenPassword);
        user.setTokenPassword(tokenPassword);
        userService.save(user);
        emailService.sendEmailTemplate(dto);

        response.setStatus(200);
        response.setData("Te hemos enviado un corrreo con un link para cambiar la contrasena");
        return response;
    }

    @PostMapping("/changePassword")
    public Response<String> changePassword(@Valid @RequestBody ChangePasswordDTO dto, BindingResult bindingResult){
        Response response = new Response();

        if(bindingResult.hasFieldErrors()) {
            response.setUserMessage("Campos mal diligenciados");
            response.setStatus(400);
            response.setData(bindingResult.getAllErrors().toString());
            return response;
            /*return new ResponseEntity("Campos mal diligenciados", HttpStatus.BAD_REQUEST);*/
        }
        if(!dto.getPassword().equals(dto.getConfirmPassword())) {
            response.setUserMessage("Las contraseñas no coinciden");
            response.setStatus(400);
            return response;
            /*return new ResponseEntity("Las contraseñas no coinciden", HttpStatus.BAD_REQUEST);*/
        }
        Optional<User> userOptional = userService.getByTokenPassword(dto.getTokenPassword());
        if(!userOptional.isPresent()) {
            response.setUserMessage("No existe ningun usuario con estas credenciales ");
            response.setStatus(404);
            return response;
            /*return new ResponseEntity("No existe ningun usuario con estas credenciales ", HttpStatus.NOT_FOUND);*/
        }

        User user = userOptional.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(newPassword);
        user.setTokenPassword(null);
        userService.save(user);

        response.setStatus(200);
        response.setData("Se cambio la contraseña exitosamente");

        return response;

    }

}
