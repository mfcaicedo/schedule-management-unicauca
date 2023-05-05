package com.pragma.api.services;

import com.pragma.api.domain.EnvironmentDTO;

public class IReportService {
    
    List<EnvironmentDTO> findAllEnvironment();

    EnviromentDTO findById(String id);
}
