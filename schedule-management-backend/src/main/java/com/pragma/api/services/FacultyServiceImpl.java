package com.pragma.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pragma.api.domain.FacultyDTO;
import com.pragma.api.model.Faculty;
import com.pragma.api.repository.IFacultyRepository;
import com.pragma.api.util.exception.ScheduleBadRequestException;

@Service
public class FacultyServiceImpl implements IFacultyService{


    private final ModelMapper modelMapper;

    private final IFacultyRepository iFacultyRepository;

    @Autowired
    public FacultyServiceImpl(ModelMapper modelMapper, IFacultyRepository iFacultyRepository) {
        this.modelMapper = modelMapper;
        this.iFacultyRepository = iFacultyRepository;
    }

    @Override
    public List<FacultyDTO> findAllByFaculty() {
        List<Faculty> facultys = this.iFacultyRepository.findAll();

        if(facultys.isEmpty()) throw new ScheduleBadRequestException("bad.request.faculty.empty", "");

        return facultys.stream().map(program ->  modelMapper.map(program, FacultyDTO.class)).collect(Collectors.toList());
        
    }
    
}
