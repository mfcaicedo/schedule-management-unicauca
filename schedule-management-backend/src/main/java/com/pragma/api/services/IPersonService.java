package com.pragma.api.services;

import com.pragma.api.domain.EventDTO;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.PersonDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPersonService {
    GenericPageableResponse findAllPerson(Pageable pageable);

    public Response<GenericPageableResponse> findAllByPersonType(Pageable pageable, String personType);
}
