package com.pragma.api.services;

import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.model.Department;
import com.pragma.api.domain.*;



import com.pragma.api.domain.DepartmentDTO;

import java.util.List;

public interface IDepartmentService {
    public List<DepartmentDTO> findAll();

    public Department findDepartmentByDepartmentName(String name);

    public Response<List<DepartmentDTO>> findAllByFacultyId(String faculty_Id);

}
