package com.pragma.api.services;

import com.pragma.api.domain.ProgramDTO;
import com.pragma.api.domain.Response;

import java.util.List;

public interface IProgramService {
    List<ProgramDTO> findAllProgram();

    ProgramDTO findByProgramId(String id);

    //Metodo para consultar todos los programas, que esten asociados al id de facultad pasado como parametro
    public Response<List<ProgramDTO>> findAllProgramByFacultyId(String facultyId);
    
    public Response<List<ProgramDTO>> findAllByDepartmentId(String department_id);

}
