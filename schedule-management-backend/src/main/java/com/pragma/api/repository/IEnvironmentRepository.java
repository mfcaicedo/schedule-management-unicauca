package com.pragma.api.repository;

import com.pragma.api.model.Environment;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IEnvironmentRepository extends JpaRepository<Environment, Integer> {

    Page<Environment> findAllByFacultyFacultyId(String facultyId, Pageable pageable);

    Page<Environment> findAllByEnvironmentType(EnvironmentTypeEnumeration environmentType, Pageable pageable);

    Page<Environment> findAllByAvailableResourcesId(Integer resourceId, Pageable pageable);

}
