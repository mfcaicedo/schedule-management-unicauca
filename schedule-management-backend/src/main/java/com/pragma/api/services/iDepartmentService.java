package com.pragma.api.services;

import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.domain.PersonDTO;

import java.util.List;

public interface iDepartmentService {
    List<DepartmentDTO> findAllDepartments();
}
