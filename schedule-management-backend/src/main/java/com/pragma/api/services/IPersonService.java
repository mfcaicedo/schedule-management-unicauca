package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.PersonDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPersonService {
    GenericPageableResponse findAllPerson(Pageable pageable);

    PersonDTO findByCode(String code);

}
