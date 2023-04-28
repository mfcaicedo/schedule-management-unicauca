package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.ResourceDTO;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.model.Resource;
import com.pragma.api.model.enums.ResourceTypeEnumeration;
import com.pragma.api.repository.IResourceRepository;
import com.pragma.api.util.PageableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements IResourceService{

    private final ModelMapper modelMapper;

    private final IResourceRepository resourceRepository;

    private final IEnvironmentService environmentService;

    @Autowired
    public ResourceServiceImpl(IResourceRepository resourceRepository, ModelMapper modelMapper, IEnvironmentService environmentService){
        this.resourceRepository = resourceRepository;
        this.modelMapper = modelMapper;
        this.environmentService = environmentService;
    }

    @Override
    public ResourceDTO saveResource(final ResourceDTO save) {
        Resource request = modelMapper.map(save, Resource.class);
        return modelMapper.map(this.resourceRepository.save(request), ResourceDTO.class);
    }

    @Override
    public GenericPageableResponse findAllResource(final Pageable pageable) {
        Page<Resource> resourcesPage = this.resourceRepository.findAll(pageable);
        if(resourcesPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.resource.empty", "");
        return this.validatePageList(resourcesPage);
    }

    @Override
    public GenericPageableResponse findAllResourceByEnvironment(final Integer environmentId, final Pageable pageable) {
        this.environmentService.findById(environmentId);
        Page<Resource> resourcesByEnvironmentPage = this.resourceRepository.findAllByResourceLocationsId(environmentId,pageable);
        if(resourcesByEnvironmentPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.resource.empty", "");
        return this.validatePageList(resourcesByEnvironmentPage);
    }

    @Override
    public GenericPageableResponse findAllResourceByResourceType(String resourceType, Pageable pageable) {
        ResourceTypeEnumeration resourceTypeEnum;
        try {
            resourceTypeEnum = ResourceTypeEnumeration.valueOf(resourceType);
        }catch (Exception e){
            throw new ScheduleBadRequestException("bad.request.resource.type", resourceType);
        }
        Page<Resource> resourcesByTypePage = this.resourceRepository.findAllByResourceType(resourceTypeEnum,pageable);
        if(resourcesByTypePage.isEmpty()) throw new ScheduleBadRequestException("bad.request.resource.empty", "");
        return this.validatePageList(resourcesByTypePage);
    }

    @Override
    public List<ResourceTypeEnumeration> findAllTypesEnvironment() {

        List<ResourceTypeEnumeration> enums = new ArrayList<ResourceTypeEnumeration>();

        enums.add(ResourceTypeEnumeration.PEDAGOGICO);
        enums.add(ResourceTypeEnumeration.TECNOLOGICO);

        return enums;
    }

    @Override
    public ResourceDTO getResourceById(final Integer code)  {
        Optional<Resource> dbResource = this.resourceRepository.findById(code);
        if(dbResource.isEmpty()) throw new ScheduleBadRequestException("bad.request.resource.id", code.toString());
        return modelMapper.map(dbResource, ResourceDTO.class);
    }

    @Override
    public ResourceDTO updateResource(final Integer code, final ResourceDTO update) {
        Optional<Resource> resourceOpt = this.resourceRepository.findById(code);
        if(resourceOpt.isEmpty()) throw new ScheduleBadRequestException("bad.request.resource.id", code.toString());
        Resource db = resourceOpt.get();
        db.setName(update.getName());
        db.setResourceType(update.getResourceType());
        return modelMapper.map(this.resourceRepository.save(db), ResourceDTO.class);
    }

    @Override
    public Boolean deleteResource(final Integer code) {
        Optional<Resource> resourceOpt = this.resourceRepository.findById(code);
        if(resourceOpt.isEmpty()) throw new ScheduleBadRequestException("bad.request.resource.id", code.toString());
        this.resourceRepository.deleteById(code);
        return true;
    }



    private GenericPageableResponse validatePageList(Page<Resource> resourcesPage){
        List<ResourceDTO> resourcesDTOS = resourcesPage.stream().map(x->modelMapper.map(x, ResourceDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(resourcesPage, resourcesDTOS);
    }
}
