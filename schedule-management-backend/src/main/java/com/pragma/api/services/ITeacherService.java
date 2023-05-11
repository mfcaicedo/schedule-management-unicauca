package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ITeacherService {
    GenericPageableResponse findAllTeacher(Pageable pageable);

}

