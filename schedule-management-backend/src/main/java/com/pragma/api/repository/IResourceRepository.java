package com.pragma.api.repository;

import com.pragma.api.model.Resource;
import com.pragma.api.model.enums.ResourceTypeEnumeration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResourceRepository extends JpaRepository<Resource, Integer> {
    Page<Resource> findAllByResourceLocationsId(Integer environmentId, Pageable pageable);
    Page<Resource> findAllByResourceType(ResourceTypeEnumeration resourceType, Pageable pageable);


}
