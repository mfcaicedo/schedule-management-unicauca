package com.pragma.api.mapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class MapperBean {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

