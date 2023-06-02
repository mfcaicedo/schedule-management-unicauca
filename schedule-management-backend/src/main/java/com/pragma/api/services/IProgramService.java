package com.pragma.api.services;

import com.pragma.api.domain.ProgramDTO;
import com.pragma.api.domain.Response;

import java.util.List;

public interface IProgramService {
    List<ProgramDTO> findAllProgram();

    ProgramDTO findByProgramId(String id);

    public Response<List<ProgramDTO>> findAllByDepartmentId(Integer department_id);

}
