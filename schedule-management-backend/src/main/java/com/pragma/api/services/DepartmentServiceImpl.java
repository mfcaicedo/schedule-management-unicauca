package com.pragma.api.services;

import com.pragma.api.domain.DepartmentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.pragma.api.domain.PersonDTO;
import com.pragma.api.model.Department;
import com.pragma.api.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements iDepartmentService{

    private final IDeparmentRepository iDeparmentRepository;

    private final ModelMapper modelMapper;


    public DepartmentServiceImpl(IDeparmentRepository iDeparmentRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.iDeparmentRepository = iDeparmentRepository;
    }

    @Override
    public List<DepartmentDTO> findAllDepartments() {
        List<Department> departments = this.iDeparmentRepository.findAll();
        List<DepartmentDTO> departmentsDTO = new ArrayList<>();
        departmentsDTO = departments.stream().map(x->modelMapper.map(x, DepartmentDTO.class)).collect(Collectors.toList());
        return departmentsDTO;
    }
}
