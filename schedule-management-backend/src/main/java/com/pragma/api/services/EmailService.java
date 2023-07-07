package com.pragma.api.services;

import com.pragma.api.domain.EmailValuesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    TemplateEngine templateEngine;

    @Value("${mail.urlFront}")
    private String urlFront;

    public void sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("drlook92@gmail.com");
        message.setTo("drlook92@gmail.com");
        message.setSubject("Prueba envio mail simple ");
        message.setText("Este es el contenido de prueba del email");

        javaMailSender.send(message);
    }

    public void sendEmailTemplate(EmailValuesDTO dto){
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("username",dto.getUsername());
            model.put("url",urlFront + dto.getToken());
            context.setVariables(model);
            String htmlText = templateEngine.process("emailTemplate",context);
            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlText, true);

            javaMailSender.send(message);
        }catch(MessagingException e){

        }
    }
}
