package com.pragma.api.controllers;

import com.pragma.api.domain.EmailValuesDTO;
import com.pragma.api.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("/email/send")
    public ResponseEntity<?> sendEmail(){
        emailService.sendEmail();
        return new ResponseEntity("Correo Enviado con exito", HttpStatus.OK);
    }

    @PostMapping("/email/sendHtml")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto){
        emailService.sendEmailTemplate(dto);
        return new ResponseEntity("Correo con link Enviado con exito", HttpStatus.OK);
    }

}
