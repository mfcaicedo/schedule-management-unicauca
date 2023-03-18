package com.pragma.api.business;

import com.pragma.api.domain.EnvironmentDTO;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.ResourceList;
import com.pragma.api.domain.Response;
import com.pragma.api.exception.ScheduleBadRequestException;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Resource;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IResourceRepository;
import com.pragma.api.util.PageableUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvironmentServiceImpl implements IEnvironmentService {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IEnvironmentRepository environmentRepository;
    @Autowired
    private IResourceRepository resourceRepository;


    @Override
    public Response<EnvironmentDTO> createEnvironment(EnvironmentDTO environmentDTO) {
        logger.debug("Init createEnvironment Business Environment: {}", environmentDTO.toString());
        Response<EnvironmentDTO> response = new Response<>();

        Environment environment = modelMapper.map(environmentDTO,Environment.class);

        EnvironmentDTO environmentDTO1 = modelMapper.map(this.environmentRepository.save(environment),EnvironmentDTO.class);

        response.setStatus(200);
        response.setUserMessage("Environment created");
        response.setDeveloperMessage("Environment created");
        response.setMoreInfo("localhost:8080/api/subject");
        response.setErrorCode("");
        response.setData(environmentDTO1);
        logger.debug("Finish create Environment Business");

        return response;
    }

    @Override
    public Response<EnvironmentDTO> getEnvironmentByCode(Integer code) {

        if(!this.environmentRepository.existsById(code)) throw  new ScheduleBadRequestException("bad.request.environment.id", Integer.toString(code));


        Environment environment = this.environmentRepository.findById(code).get();

        EnvironmentDTO environmentDTO1 = modelMapper.map(environment,EnvironmentDTO.class);

        Response<EnvironmentDTO> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Resource added successfully");
        response.setDeveloperMessage("Resource added successfully");
        response.setMoreInfo("localhost:8080/api/subject");
        response.setErrorCode("");
        response.setData(environmentDTO1);

        return response;
    }

    @Override
    public Response<GenericPageableResponse> findAll(Pageable pageable) {
        Page<Environment> environmentPage = this.environmentRepository.findAll(pageable);
        if(environmentPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.subject.empty", "");

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Environments found");
        response.setDeveloperMessage("Environments found");
        response.setMoreInfo("localhost:8080/api/environment");
        response.setErrorCode("");
        response.setData(this.validatePageList(environmentPage));

        return response;
    }

    @Override
    public Environment findById(Integer id) {
        Environment environment = this.environmentRepository.findById(id).orElseThrow(()->new ScheduleBadRequestException("bad.request.environment.id", id.toString()));
        return environment;
    }

    @Override
    public List<EnvironmentTypeEnumeration> findAllTypesEnvironment() {

        List<EnvironmentTypeEnumeration> enums = new ArrayList<EnvironmentTypeEnumeration>();

        enums.add(EnvironmentTypeEnumeration.AUDITORIO);
        enums.add(EnvironmentTypeEnumeration.LABORATORIO);
        enums.add(EnvironmentTypeEnumeration.SALON);

        return enums;
    }

    @Override
    public Response<Boolean> addResourceToEnvironment(ResourceList resourceList, Integer environmentId) {
        if(!this.environmentRepository.existsById(environmentId)) throw  new ScheduleBadRequestException("bad.request.environment.id", Integer.toString(environmentId));
        Environment environment = this.environmentRepository.findById(environmentId).get();
        for (int resourceId:resourceList.getResourceList()) {
            if(!this.resourceRepository.existsById(resourceId)) throw  new ScheduleBadRequestException("bad.request.resource.id", Integer.toString(environmentId));

            Resource resourceToAdd = this.resourceRepository.findById(resourceId).get();
            environment.getAvailableResources().add(resourceToAdd);
        }

        this.environmentRepository.save(environment);

        Response<Boolean> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Resource added successfully");
        response.setDeveloperMessage("Resource added successfully");
        response.setMoreInfo("localhost:8080/api/subject");
        response.setErrorCode("");
        response.setData(true);

        return response;
    }

    @Override
    public Response<Boolean> updateResourceToEnvironment(ResourceList resourceList, Integer environmentId) {
        if(!this.environmentRepository.existsById(environmentId)) throw  new ScheduleBadRequestException("bad.request.environment.id", Integer.toString(environmentId));
        Environment environment = this.environmentRepository.findById(environmentId).get();
        environment.getAvailableResources().clear();
        for (int resourceId:resourceList.getResourceList()) {
            if(!this.resourceRepository.existsById(resourceId)) throw  new ScheduleBadRequestException("bad.request.resource.id", Integer.toString(environmentId));

            Resource resourceToAdd = this.resourceRepository.findById(resourceId).get();
            environment.getAvailableResources().add(resourceToAdd);
        }

        this.environmentRepository.save(environment);

        Response<Boolean> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Resource added successfully");
        response.setDeveloperMessage("Resource added successfully");
        response.setMoreInfo("localhost:8080/api/subject");
        response.setErrorCode("");
        response.setData(true);

        return response;
    }

    @Override
    public Response<GenericPageableResponse> findAllByResourceId(Pageable pageable, Integer resourceId) {
        Page<Environment> environmentPage = this.environmentRepository.findAllByAvailableResourcesId(resourceId, pageable);
        if(environmentPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.environment.empty", "");

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Environments found");
        response.setDeveloperMessage("Environments found");
        response.setMoreInfo("localhost:8080/api/environment");
        response.setErrorCode("");
        response.setData(this.validatePageList(environmentPage));

        return response;
    }

    @Override
    public Response<GenericPageableResponse> findAllByFacultyId(Pageable pageable, String facultyId) {

        Page<Environment> environmentPage = this.environmentRepository.findAllByFacultyFacultyId(facultyId, pageable);
        if(environmentPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.environment.empty", "");

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Environments found");
        response.setDeveloperMessage("Environments found");
        response.setMoreInfo("localhost:8080/api/environment");
        response.setErrorCode("");
        response.setData(this.validatePageList(environmentPage));

        return response;
    }

    @Override
    public Response<GenericPageableResponse> findAllByEnvironmentType(Pageable pageable, EnvironmentTypeEnumeration environmentType) {
        Page<Environment> environmentPage = this.environmentRepository.findAllByEnvironmentType(environmentType, pageable);
        if(environmentPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.environment.empty", "");

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Environments found");
        response.setDeveloperMessage("Environments found");
        response.setMoreInfo("localhost:8080/api/environment");
        response.setErrorCode("");
        response.setData(this.validatePageList(environmentPage));

        return response;
    }

    private GenericPageableResponse validatePageList(Page<Environment> environmentsPage){
        List<EnvironmentDTO> environmentDTOS = environmentsPage.stream().map(x->modelMapper.map(x, EnvironmentDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(environmentsPage, environmentDTOS);
    }
}
