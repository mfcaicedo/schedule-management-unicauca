package com.pragma.api.services;

import com.pragma.api.domain.EnvironmentDTO;
import com.pragma.api.domain.EnvironmentResourceDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.EnvironmentResource;
import com.pragma.api.repository.IEnvironmentResourceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentResourceServiceImpl implements IEnvironmentResourceService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IEnvironmentResourceRepository environmentResourceRepository;


    @Override
    public Response<EnvironmentResourceDTO> saveEnvironmentResource(EnvironmentResourceDTO environmentResourceDTO) {
        EnvironmentResource environmentResource = modelMapper.map(environmentResourceDTO, EnvironmentResource.class);
        Response<EnvironmentResourceDTO> response = new Response<>();
        EnvironmentResourceDTO environmentResourceDTO1 = modelMapper.map(this.environmentResourceRepository.save(environmentResource), EnvironmentResourceDTO.class);

        response.setStatus(200);
        response.setUserMessage("EnvironmentResource created");
        response.setDeveloperMessage("EnvironmentResource created");

        return response;
    }
}
