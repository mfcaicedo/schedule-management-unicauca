package com.pragma.api.services;

import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.model.Department;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentDTO> findAll();

    Department findDepartmentByDepartmentName(String name);
}
