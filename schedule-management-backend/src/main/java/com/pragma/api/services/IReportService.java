package com.pragma.api.services;

import java.util.List;

import com.pragma.api.domain.EnvironmentDTO;


public interface IReportService {
    
    List<EnvironmentDTO> findAllEnvironment();

    EnvironmentDTO findById(String id);
}


