package com.pragma.api.services;

import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.domain.ProgramDTO;
import com.pragma.api.model.Department;
import com.pragma.api.repository.IDeparmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements IDepartmentService{
    @Autowired
    private IDeparmentRepository departmentRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<DepartmentDTO> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(department ->  modelMapper.map(department, DepartmentDTO.class)).collect(Collectors.toList());
    }
    @Override
    public List<DepartmentDTO> findAllDepartments() {
        List<Department> departments = this.departmentRepository.findAll();
        List<DepartmentDTO> departmentsDTO = new ArrayList<>();
        departmentsDTO = departments.stream().map(x->modelMapper.map(x, DepartmentDTO.class)).collect(Collectors.toList());
        return departmentsDTO;
    }
}
