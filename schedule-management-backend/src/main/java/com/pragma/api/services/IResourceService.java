package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.ResourceDTO;
import com.pragma.api.model.enums.ResourceTypeEnumeration;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IResourceService{
    ResourceDTO saveResource(ResourceDTO save);
    GenericPageableResponse findAllResource(Pageable pageable);
    GenericPageableResponse findAllResourceByEnvironment(Integer environmentId, Pageable pageable);

    ResourceDTO getResourceById(Integer code);
    ResourceDTO updateResource(Integer code, ResourceDTO update);
    Boolean deleteResource(Integer code);

    GenericPageableResponse findAllResourceByResourceType(String resourceType, Pageable pageable);
    List<ResourceTypeEnumeration> findAllTypesEnvironment();


}
