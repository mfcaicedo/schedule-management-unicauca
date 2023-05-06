package com.pragma.api.services;

import com.pragma.api.domain.FacultyDTO;
import java.util.List;

public interface IFacultyService {
    List<FacultyDTO> findAllByFaculty();
}
