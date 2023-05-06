package com.pragma.api.services;

import java.util.List;

import com.pragma.api.domain.EnvironmentDTO;
import java.util.List;

public interface IReportService {
    
    List<EnvironmentDTO> findAllEnvironment();

    EnvironmentDTO findById(String id);
}


