package com.pragma.api.services;

import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.model.Department;
import com.pragma.api.repository.IDeparmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.domain.EnvironmentDTO;
import com.pragma.api.domain.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.pragma.api.util.PageableUtils;


@Service
public class DepartmentServiceImpl implements IDepartmentService{
    @Autowired
    private IDeparmentRepository departmentRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<DepartmentDTO> findAll() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentsDTO = new ArrayList<>();
        departmentsDTO = departments.stream().map(department ->  modelMapper.map(department, DepartmentDTO.class)).collect(Collectors.toList());
        return departmentsDTO;
    }

    @Override
    public Department findDepartmentByDepartmentName(String name) {
        return departmentRepository.findDepartmentByDepartmentName(name);
    }

    @Override
    public Response<List<DepartmentDTO>> findAllByFacultyId(String faculty_Id) {

        Response<List<DepartmentDTO>> response = new Response<>();
        List<Department> departmentList = this.departmentRepository.findByFacultyId(faculty_Id);
        List<DepartmentDTO> departmentDTOlist = modelMapper.map(departmentList, new org.modelmapper.TypeToken<List<DepartmentDTO>>() {
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
