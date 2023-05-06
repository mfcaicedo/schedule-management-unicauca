package com.pragma.api.services;

import com.pragma.api.domain.ProgramDTO;


import java.util.List;

public interface IProgramService {
    List<ProgramDTO> findAllProgram();

    ProgramDTO findByProgramId(String id);

}
