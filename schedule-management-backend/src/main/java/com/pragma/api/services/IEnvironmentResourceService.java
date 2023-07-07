package com.pragma.api.services;

import com.pragma.api.domain.EnvironmentResourceDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.EnvironmentResource;

public interface IEnvironmentResourceService {

    public Response<EnvironmentResourceDTO> saveEnvironmentResource(EnvironmentResourceDTO  enviromentResourceDTO);
}