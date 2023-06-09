package com.pragma.api.services;

import com.pragma.api.domain.FacultyDTO;
import com.pragma.api.domain.Response;

import java.util.List;

public interface IFacultyService {
    List<FacultyDTO> findAllByFaculty();
    //Buscar por id de facultad
    public Response<FacultyDTO> findByFacultyId(String facultyId);
    public Response<List<FacultyDTO>> findAllFaculty();
}
