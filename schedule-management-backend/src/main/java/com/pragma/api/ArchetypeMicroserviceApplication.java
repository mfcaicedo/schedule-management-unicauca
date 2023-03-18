package com.pragma.api;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Clase principal de la aplicacion. Permite ejecutar el proyecto.
 */
@EnableSwagger2
@EnableWebSecurity
@SpringBootApplication
public class ArchetypeMicroserviceApplication {

    /**
     * Metodo encargado de ejecutar la aplicación Spring boot
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ArchetypeMicroserviceApplication.class, args);
    }

}
