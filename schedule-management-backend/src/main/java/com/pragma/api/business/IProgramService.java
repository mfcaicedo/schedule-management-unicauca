package com.pragma.api.business;

import com.pragma.api.domain.ProgramDTO;


import java.util.List;

public interface IProgramService {
    List<ProgramDTO> findAllProgram();

    ProgramDTO findByProgramId(String id);

}
