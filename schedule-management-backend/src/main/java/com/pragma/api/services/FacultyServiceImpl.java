package com.pragma.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pragma.api.domain.FacultyDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.Faculty;
import com.pragma.api.repository.IFacultyRepository;
import com.pragma.api.util.exception.ScheduleBadRequestException;

@Service
public class FacultyServiceImpl implements IFacultyService{


    private final ModelMapper modelMapper;

    private final IFacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(ModelMapper modelMapper, IFacultyRepository facultyRepository) {
        this.modelMapper = modelMapper;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<FacultyDTO> findAllByFaculty() {
        List<Faculty> facultys = this.facultyRepository.findAll();

        if(facultys.isEmpty()) throw new ScheduleBadRequestException("bad.request.faculty.empty", "");

        return facultys.stream().map(program ->  modelMapper.map(program, FacultyDTO.class)).collect(Collectors.toList());
        
    }

    @Override
    public Response<FacultyDTO> findByFacultyId(String facultyId) {
        if(!this.facultyRepository.existsFacultyByFacultyId(facultyId)) throw  new ScheduleBadRequestException("bad.request.faculty.faculty_id","");

        Faculty faculty = this.facultyRepository.findByFacultyId(facultyId);
        FacultyDTO facultyDTO1 = modelMapper.map(faculty,FacultyDTO.class);
        Response<FacultyDTO> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Faculty Finded successfully");
        response.setDeveloperMessage("Faculty Finded successfully");
        response.setMoreInfo("localhost:8081/api/faculty(toDO)");
        response.setErrorCode("");
        response.setData(facultyDTO1);
        return response;
    }
    
}
