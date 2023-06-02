package com.pragma.api.services;

import com.pragma.api.repository.IDeparmentRepository;
import com.pragma.api.model.Department;
import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.domain.EnvironmentDTO;
import com.pragma.api.domain.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pragma.api.util.PageableUtils;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartmentServiceImpl implements IDepartmentService{

    private static final Logger logger = LoggerFactory.getLogger(EnvironmentServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IDeparmentRepository departmentRepository;





    @Override
    public Response<List<DepartmentDTO>> findAllByFacultyId(String faculty_Id) {

        Response<List<DepartmentDTO>> response = new Response<>();
        List<Department> departmentList = this.departmentRepository.findByFacultyId(faculty_Id);
        List<DepartmentDTO> departmentDTOlist = modelMapper.map(departmentList, new org.modelmapper.TypeToken<List<EnvironmentDTO>>() {
        }.getType());
        response.setStatus(200);
        response.setUserMessage("List of Availability Departments Finded successfully");
        response.setDeveloperMessage("List of Availability Departments Finded successfully");
        response.setMoreInfo("localhost:8080/api/department(toDO)");
        response.setErrorCode("");
        response.setData(departmentDTOlist);
        return response;
    }
    
}
