package com.pragma.api.services;

import com.pragma.api.domain.*;



import com.pragma.api.domain.DepartmentDTO;

import java.util.List;

public interface IDepartmentService {

    public Response<List<DepartmentDTO>> findAllByFacultyId(String faculty_Id);

}
