package com.pragma.api.business;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.ProgramDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITeacherService {
    GenericPageableResponse findAllTeacher(Pageable pageable);
}
