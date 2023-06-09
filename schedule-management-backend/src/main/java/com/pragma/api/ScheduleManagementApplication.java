package com.pragma.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase principal de la aplicacion. Permite ejecutar el proyecto.
 */
@EnableSwagger2
@EnableWebSecurity
@SpringBootApplication
public class ScheduleManagementApplication {

    /**
     * Metodo encargado de e jecutar la aplicación Spring boot
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ScheduleManagementApplication.class, args);
    }

}